package com.hzgy.db.mybatis;

import com.github.pagehelper.PageHelper;
import com.hzgy.core.common.DataSourceType;
import com.hzgy.core.spring.SpringContextUtil;
import com.hzgy.core.util.StringUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
public class MybatisConfiguration{

    private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

    /**
     * 读库数量
     */
    @Value("${mysql.datasource.readSize}")
    private String readDataSourceSize;

    /**
     * mybatis po 文件存放路径
     */
    @Value("${mysql.datasource.typeAliasesPackage}")
    private String typeAliasesPackage;

    /**
     * mybatis mapper 文件存放路径
     */
    @Value("${mysql.datasource.mapperLocations}")
    private String mapperLocations;

    /**
     * mybatis全局配置文件
     */
    @Value("${mysql.datasource.configLocation}")
    private String configLocation;

    /**
     * 分页插件配置信息-数据库方言
     */
    @Value("${pagehelper.helper-dialect}")
    private String helperDialect;


    /**
     * 分页插件配置信息-分页合理化
     */
    @Value("${pagehelper.reasonable}")
    private String reasonable;

    /**
     * 分页插件配置信息-是否进行count查询
     */
    @Value("${pagehelper.row-bounds-with-count}")
    private String rowBoundsWithCount;

    /**
     * 分页插件配置信息-参数offset是否作为PageNum使用
     */
    @Value("${pagehelper.offset-as-page-num}")
    private String offsetAsPageNum;

    /**
     * 分页插件配置信息-检查返回类型是否为PageInfo,none返回Page
     */
    @Value("${pagehelper.returnPageInfo}")
    private String returnPageInfo;

    /**
     * 分页插件配置信息-是否支持接口参数来传递分页参数
     */
    @Value("${pagehelper.support-methods-arguments}")
    private String supportMethodsArguments;

    /**
     * 分页插件配置信息-参数offset是否作为PageNum使用
     */
    @Value("${pagehelper.params}")
    private String params;

    @Autowired
    private DataSource writeDataSource;

    @Autowired
    @Qualifier("readDataSources")
    private List<DataSource> readDataSources;


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        logger.info("mybatis sqlSessionFactory init...");
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
            // 读取数据库po存放路径配置
            if (!StringUtil.isEmpty(typeAliasesPackage)) {
                sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            }
            //设置mapper.xml文件所在位置
            if (!StringUtil.isEmpty(mapperLocations)) {
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
                sessionFactoryBean.setMapperLocations(resources);
            }
            //设置mybatis-config.xml配置文件位置
            //如果typeAliasesPackage和mapperLocations都为空
            //则所有配置在mybatis-config文件中统一配置（本系统推荐这么使用，由生成程序统一生成出来）
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
            //添加分页插件、打印sql插件
//            Interceptor[] plugins = new Interceptor[]{new SqlPrintInterceptor()};
//            sessionFactoryBean.setPlugins(plugins);
            SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
            logger.info("mybatis sqlSessionFactory init success");
            return sqlSessionFactory;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error:", e);
            return null;
        }
    }

    /**
     * 分页插件
     *
     * @return 返回分页对象
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //设置值，来源于datasource.property配置文件
        //任何一项值为空，该值不进行设置，使用默认值
        if (!StringUtil.isEmpty(mapperLocations)) {
            properties.setProperty("mapperLocations", mapperLocations);
        }
        if (!StringUtil.isEmpty(reasonable)) {
            properties.setProperty("reasonable", reasonable);
        }
        if (!StringUtil.isEmpty(rowBoundsWithCount)) {
            properties.setProperty("rowBoundsWithCount", rowBoundsWithCount);
        }
        if (!StringUtil.isEmpty(offsetAsPageNum)) {
            properties.setProperty("offsetAsPageNum", offsetAsPageNum);
        }
        if (!StringUtil.isEmpty(returnPageInfo)) {
            properties.setProperty("returnPageInfo", returnPageInfo);
        }
        if (!StringUtil.isEmpty(supportMethodsArguments)) {
            properties.setProperty("supportMethodsArguments", supportMethodsArguments);
        }
        if (!StringUtil.isEmpty(params)) {
            properties.setProperty("params", params);
        }
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    /**
     * 把所有数据库都放在路由中
     *
     * @return 返回路由数据源
     */
    @Bean(name = "roundRobinDataSouceProxy")
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        final int readSize = Integer.parseInt(readDataSourceSize);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，
        //否则切换数据源时找不到正确的数据源
        targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
        //动态获取并设置多读数据源信息
        for (int i = 0; i < readDataSources.size(); i++) {
            DataSource dataSource = readDataSources.get(i);
            targetDataSources.put(DataSourceType.read.getType() + (i + 1), dataSource);
        }
        //路由类，寻找对应的数据源
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource() {
            private Logger logger = LoggerFactory.getLogger(AbstractRoutingDataSource.class);
            private AtomicInteger count = new AtomicInteger(0);

            /**
             * 这是AbstractRoutingDataSource类中的一个抽象方法，
             * 而它的返回值是你所要用的数据源dataSource的key值，有了这个key值，
             * targetDataSources就从中取出对应的DataSource，如果找不到，就用配置默认的数据源。
             */
            @Override
            protected Object determineCurrentLookupKey() {
                String typeKey = DataSourceContextHolder.getReadOrWrite();
                //如果未设置是读库还是写库，默认使用读库
                if (typeKey == null) {
                    logger.info("don't set datasource type,use default type write");
                    return DataSourceType.write.getType();
                }
                //判断是否为读库，如果是直接返回读库类型
                if (typeKey.equals(DataSourceType.write.getType())) {
                    return DataSourceType.write.getType();
                }
                //读库， 简单负载均衡
                int number = count.getAndAdd(1);
                int lookupKey = number % readSize;
                return DataSourceType.read.getType() + (lookupKey + 1);
            }
        };
        //默认库
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //事务管理
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSource dataSource = SpringContextUtil.getBean("roundRobinDataSouceProxy");
        return new DataSourceTransactionManager(dataSource);
    }
}
