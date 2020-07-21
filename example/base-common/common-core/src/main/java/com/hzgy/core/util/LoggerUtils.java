package com.hzgy.core.util;

import com.hzgy.core.common.constant.MDCConstants;
import org.slf4j.Logger;
import org.slf4j.MDC;

public class LoggerUtils {

    public static void info(Logger logger, String msg){
        logger.info("[{}] {}", MDC.get(MDCConstants.PACKAGE_NAME),msg);
    }

    public static void info(Logger logger, String format,String msg1){
        logger.info("[{}] "+format, MDC.get(MDCConstants.PACKAGE_NAME),msg1);
    }

    public static void info(Logger logger, String format,String msg1,String msg2){
        logger.info("[{}] "+format, MDC.get(MDCConstants.PACKAGE_NAME),msg1,msg2);
    }

    public static void info(Logger logger, String format,Object... obj){
        logger.info("[{}] "+format, MDC.get(MDCConstants.PACKAGE_NAME),obj);
    }

    public static void error(Logger logger, String msg){
        logger.error("[{}] {}", MDC.get(MDCConstants.PACKAGE_NAME),msg);
    }

    public static void error(Logger logger, String format,String msg1){
        logger.error("[{}] "+format, MDC.get(MDCConstants.PACKAGE_NAME),msg1);
    }

    public static void error(Logger logger, String format,String msg1,String msg2){
        logger.error("[{}] "+format, MDC.get(MDCConstants.PACKAGE_NAME),msg1,msg2);
    }

    public static void error(Logger logger, String format,Object... obj){
        logger.error("[{}] "+format, MDC.get(MDCConstants.PACKAGE_NAME),obj);
    }

    public static void debug(Logger logger, String msg){
        logger.debug("[{}] {}", MDC.get(MDCConstants.PACKAGE_NAME),msg);
    }

    public static void debug(Logger logger, String format,Object... obj){
        logger.debug("[{}] "+format, MDC.get(MDCConstants.PACKAGE_NAME),obj);
    }
}
