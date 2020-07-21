package com.hzgy.core.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MapUtil extends BeanUtilsBean {

    /**
     * map字段排序
     * @param map 排序对象
     * @return 排序后的map
     */
    public static Map<String,String> sortMapByKey(Map<String,String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String,String> sortMap = new TreeMap<>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }

    /**
     * 排序后的字段组成一个字符串
     * @param map 待排序的map对象
     * @param sep 分割符号
     * @return 字符串
     */
    public static String sortByKeyToString(Map<String,String> map,String sep){
        if (map == null || map.size() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Map<String,String> sortMap = sortMapByKey(map);
        for (Map.Entry<String, String> entry : sortMap.entrySet()) {
            if (sep == null || sep.equals("")){
                sb.append(entry.getValue());
            }
            else {
                sb.append(entry.getValue()).append(sep);
            }
        }
        return sb.toString();
    }
}
