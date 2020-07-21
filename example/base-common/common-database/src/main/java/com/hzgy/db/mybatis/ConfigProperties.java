package com.hzgy.db.mybatis;


import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * 自定义数据源对象，继承druidDataSource
 * 处理自定义配置文件加载的问题
 * 同时处理其他配置参数不生效的问题
 */
public abstract class ConfigProperties {

    /**
     * 处理读取配置文件配置信息的方法
     *
     * @param prefix      配置文件前缀
     * @param environment 配置文件对象
     */
    public Properties convertDruidDataSourcePropety(String prefix, Environment environment) {
        Properties properties = new Properties();
        //处理自定义配置文件
        String property = environment.getProperty(prefix + ".name");
        if (property != null) {
            properties.setProperty("druid.name", property);
        }

        property = environment.getProperty(prefix + ".url");
        if (property != null) {
            properties.setProperty("druid.url", property);
        }

        property = environment.getProperty(prefix + ".username");
        if (property != null) {
            properties.setProperty("druid.username", property);
        }

        property = environment.getProperty(prefix + ".password");
        if (property != null) {
            properties.setProperty("druid.password", property);
        }

        property = environment.getProperty(prefix + ".testWhileIdle");
        if (property != null) {
            properties.setProperty("druid.testWhileIdle", property);
        }

        property = environment.getProperty(prefix + ".testOnBorrow");
        if (property != null) {
            properties.setProperty("druid.testOnBorrow", property);
        }

        property = environment.getProperty(prefix + ".validationQuery");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.validationQuery", property);
        }

        property = environment.getProperty(prefix + ".useGlobalDataSourceStat");
        if (property != null) {
            properties.setProperty("druid.useGlobalDataSourceStat", property);
        }
        property = environment.getProperty(prefix + ".useGloalDataSourceStat");
        if (property != null) {
            properties.setProperty("druid.useGloalDataSourceStat", property);
        }

        property = environment.getProperty(prefix + ".asyncInit");
        if (property != null) {
            properties.setProperty("druid.asyncInit", property);
        }

        property = environment.getProperty(prefix + ".filters");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.filters", property);
        }

        property = environment.getProperty(prefix + ".timeBetweenLogStatsMillis");
        long value;
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.timeBetweenLogStatsMillis", property);
        }

        property = environment.getProperty(prefix + ".stat.sql.MaxSize");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.stat.sql.MaxSize", property);
        }

        property = environment.getProperty(prefix + ".clearFiltersEnable");
        if (property != null) {
            properties.setProperty("druid.clearFiltersEnable", property);
        }
        property = environment.getProperty(prefix + ".resetStatEnable");
        if (property != null) {
            properties.setProperty("druid.resetStatEnable", property);
        }

        property = environment.getProperty(prefix + ".notFullTimeoutRetryCount");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.notFullTimeoutRetryCount", property);
        }

        property = environment.getProperty(prefix + ".timeBetweenEvictionRunsMillis");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.timeBetweenEvictionRunsMillis", property);
        }

        property = environment.getProperty(prefix + ".maxWaitThreadCount");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.maxWaitThreadCount", property);
        }

        property = environment.getProperty(prefix + ".failFast");
        if (property != null) {
            properties.setProperty("druid.failFast", property);
        }

        property = environment.getProperty(prefix + ".phyTimeoutMillis");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.phyTimeoutMillis", property);
        }

        property = environment.getProperty(prefix + ".minEvictableIdleTimeMillis");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.minEvictableIdleTimeMillis", property);
        }

        property = environment.getProperty(prefix + ".maxEvictableIdleTimeMillis");
        if (property != null && property.length() > 0) {
            properties.setProperty("druid.maxEvictableIdleTimeMillis", property);
        }
        property = environment.getProperty(prefix + ".keepAlive");
        if (property != null) {
            properties.setProperty("druid.keepAlive", property);
        }

        property = environment.getProperty(prefix + ".poolPreparedStatements");
        if (property != null) {
            properties.setProperty("druid.poolPreparedStatements", property);
        }

        property = environment.getProperty(prefix + ".initVariants");
        if (property != null) {
            properties.setProperty("druid.initVariants", property);
        }

        property = environment.getProperty(prefix + ".initGlobalVariants");
        if (property != null) {
            properties.setProperty("druid.initGlobalVariants", property);
        }

        property = environment.getProperty(prefix + ".useUnfairLock");
        if (property != null) {
            properties.setProperty("druid.useUnfairLock", property);
        }

        property = environment.getProperty(prefix + ".driver-class-name");
        if (property != null) {
            properties.setProperty("druid.driverClassName", property);
        }

        property = environment.getProperty(prefix + ".initialSize");
        if (property != null) {
            properties.setProperty("druid.initialSize", property);
        }

        property = environment.getProperty(prefix + ".minIdle");
        if (property != null) {
            properties.setProperty("druid.minIdle", property);
        }

        property = environment.getProperty(prefix + ".maxActive");
        if (property != null) {
            properties.setProperty("druid.maxActive", property);
        }

        property = environment.getProperty(prefix + ".killWhenSocketReadTimeout");
        if (property != null) {
            properties.setProperty("druid.killWhenSocketReadTimeout", property);
        }

        property = environment.getProperty(prefix + ".connectProperties");
        if (property != null) {
            properties.setProperty("druid.connectProperties", property);
        }

        property = environment.getProperty(prefix + ".maxPoolPreparedStatementPerConnectionSize");
        if (property != null) {
            properties.setProperty("druid.maxPoolPreparedStatementPerConnectionSize", property);
        }

        property = environment.getProperty(prefix + ".initConnectionSqls");
        if (property != null) {
            properties.setProperty("druid.initConnectionSqls", property);
        }
        return properties;
    }
}
