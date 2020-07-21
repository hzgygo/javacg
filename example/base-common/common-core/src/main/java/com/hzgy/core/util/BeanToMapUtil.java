package com.hzgy.core.util;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * bean to map 转换工具
 */
public class BeanToMapUtil {

    private static Logger log = LoggerFactory.getLogger(BeanToMapUtil.class);

    /**
     * map to bean 转换 利用org.apache.commons.beanutils 工具类实现
     * @param map map对象
     * @param obj 实体bean对象
     */
    public static void transPopulate(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            log.error("transMap2Bean2 Error ",e);
        }
    }

    /**
     * map to bean 转换 利用Introspector,PropertyDescriptor实现
     * @param map map对象
     * @param obj 实体bean对象
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    try{
                        setter.invoke(obj, value.toString());
                    }
                    catch (Exception e) {
                        Class cls = property.getPropertyType();
                        if (cls.equals(Integer.class)){
                            setter.invoke(obj, Integer.parseInt(value.toString()));
                        }
                        else if (cls.equals(Boolean.class)){
                            setter.invoke(obj, Boolean.parseBoolean(value.toString()));
                        }
                        else if (cls.equals(Long.class)){
                            setter.invoke(obj, Long.parseLong(value.toString()));
                        }
                        else if (cls.equals(Float.class)){
                            setter.invoke(obj, Float.parseFloat(value.toString()));
                        }
                        else if (cls.equals(Double.class)){
                            setter.invoke(obj, Double.parseDouble(value.toString()));
                        }
                        else if (cls.equals(Byte.class)){
                            setter.invoke(obj, Byte.parseByte(value.toString()));
                        }
                        else if (cls.equals(Date.class)){
                            setter.invoke(obj, DateUtil.parse(value.toString()));
                        }
                        else{
                            setter.invoke(obj,value.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("transMap2Bean Error ",e);
        }
        return;
    }

    /**
     * bean to map 转换 利用Introspector,PropertyDescriptor实现
     * @param obj 实体bean对象
     */
    public static Map<String, Object> transBean2Map(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            log.error("transBean2Map Error " ,e);
        }
        return map;
    }
}
