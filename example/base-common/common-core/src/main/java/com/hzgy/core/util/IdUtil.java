package com.hzgy.core.util;

public class IdUtil {
    private IdUtil(){}

    private static final String EVENT_KEY_SPLIT_STR = "_";

    private static final String ROW_KEY_SPLIT_STR = "-";

    private static final String CACHE_KEY_SPLIT_STR = ":";

    /** 获取行键 */
    public static String getRowKey(String keyId, String eventId){
        return keyId + ROW_KEY_SPLIT_STR + eventId;
    }

    public static String getCacheKey(String applicationName, Object id){
        return applicationName + CACHE_KEY_SPLIT_STR + id;
    }

    public static String getCacheKey(String applicationName, Object id1,Object id2){
        return applicationName + CACHE_KEY_SPLIT_STR + id1 + CACHE_KEY_SPLIT_STR + id2;
    }

    public static String getEventId(){
        return "E#" + SnowflakeIdWorker.nextId();
    }

    public static String getBoId(){ return "B#" + SnowflakeIdWorker.nextId(); }

    public static String getRelationId(){
        return "R#" + SnowflakeIdWorker.nextId();
    }

}
