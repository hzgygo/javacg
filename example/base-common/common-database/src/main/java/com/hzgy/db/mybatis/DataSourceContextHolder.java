package com.hzgy.db.mybatis;


import com.hzgy.core.common.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地线程内存储的数据源上下文
 */
public class DataSourceContextHolder {
    /**
     * 本地线程
     */
    private static final ThreadLocal<String> local = new ThreadLocal<String>();
    private static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读库
     */
    public static void setRead() {
        logger.info("switch to read datasource...");
        local.set(DataSourceType.read.getType());

    }

    /**
     * 写库
     */
    public static void setWrite() {
        logger.info("switch to write datasource...");
        local.set(DataSourceType.write.getType());
    }

    public static String getReadOrWrite() {
        return local.get();
    }

    public static void clear() {
        local.remove();
    }
}
