package com.hzgy.db.mybatis;


import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration extends ConfigProperties{

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    /**
     * 读库数量
     */
    @Value("${mysql.datasource.readSize}")
    private String readDataSourceSize;

    /**
     * 数据库连接池类型，目前使用阿里德鲁伊
     */
    @Value("${mysql.datasource.type}")
    private Class<? extends DataSource> dataSourceType;


    @Resource
    private Environment environment;

    /**
     * 写库 数据源配置
     *
     * @return 返回读库数据源
     */
    @Bean(name = "writeDataSource")
    @Primary
//    @ConfigurationProperties(prefix = "mysql.datasource.write")
    public DataSource writeDataSource() {
        logger.info("write datasource init...");
        //数据库配置文件前缀
        String propetyPrefix = "mysql.datasource.write";
        DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
        //目前支持阿里的德鲁伊连接池，其他连接池需要在进行特殊处理
        if (dataSource instanceof DruidDataSource) {
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
            //处理Druid数据源property配置文件
            Properties properties = super.convertDruidDataSourcePropety(propetyPrefix, environment);
            //自定义配置文件处理方法
            druidDataSource.configFromPropety(properties);
        }
        logger.info("write datasource init success!");
        return dataSource;
    }

    /**
     * 初始化读数据源
     */
    @Bean(name = "readDataSources")
    public List<DataSource> readDataSources() {
        List<DataSource> dataSources = new ArrayList<>();
        //判断读库配置的数量
        if (!StringUtils.isEmpty(readDataSourceSize)) {
            Integer count = 0;
            try {
                count = Integer.parseInt(readDataSourceSize);
            } catch (Exception e) {
                count = 0;
            }
            //只有读库数量大于0的时候，才进行读库初始化
            for (int i = 1; i <= count; i++) {
                String num = "";
                if (i < 10) {
                    num = "0" + i;
                }
                logger.info("read" + num + " datasource init...");
                //数据库配置文件前缀
                String propetyPrefix = "mysql.datasource.read" + num;
                DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
                //目前支持阿里的德鲁伊连接池，其他连接池需要在进行特殊处理
                if (dataSource instanceof DruidDataSource) {
                    DruidDataSource druidDataSource = (DruidDataSource) dataSource;
                    //处理Druid数据源property配置文件
                    Properties properties = super.convertDruidDataSourcePropety(propetyPrefix, environment);
                    //自定义配置文件处理方法
                    druidDataSource.configFromPropety(properties);
                }
                dataSources.add(dataSource);
                logger.info("read" + num + " datasource init success!");
            }
        }
        return dataSources;
    }
}
