package com.hzgy.core.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtil extends BeanUtilsBean {

    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private static BeanUtil beanUtil;

    public static BeanUtil getInstance() {
        if (beanUtil == null) {
            synchronized (BeanUtil.class) {
                if (beanUtil == null) {
                    beanUtil = new BeanUtil();
                }
            }
        }
        return beanUtil;
    }

    /**
     * @param dest           目标对象
     * @param src            源对象
     * @param ignoreNullFlag 是否忽略null值
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void copyProperties(Object dest, Object src, boolean ignoreNullFlag)
            throws IllegalAccessException, InvocationTargetException {
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (src == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("BeanUtils.copyProperties(" + dest + ", " + src + "," + ignoreNullFlag + ")");
        }
        // Copy the properties, converting as necessary
        if (src instanceof DynaBean) {
            DynaProperty[] origDescriptors =
                    ((DynaBean) src).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                // Need to check isReadable() for WrapDynaBean
                // (see Jira issue# BEANUTILS-61)
                if (getPropertyUtils().isReadable(src, name) &&
                        getPropertyUtils().isWriteable(dest, name)) {
                    Object value = ((DynaBean) src).get(name);
                    if (ignoreNullFlag) {
                        if (value != null) {
                            copyProperty(dest, name, value);
                        }
                    } else {
                        copyProperty(dest, name, value);
                    }
                }
            }
        } else if (src instanceof Map) {
            @SuppressWarnings("unchecked")
            // Map properties are always of type <String, Object>
                    Map<String, Object> propMap = (Map<String, Object>) src;
            for (Map.Entry<String, Object> entry : propMap.entrySet()) {
                String name = entry.getKey();
                Object value = entry.getValue();
                if (getPropertyUtils().isWriteable(dest, name)) {
                    if (ignoreNullFlag) {
                        if (value != null) {
                            copyProperty(dest, name, value);
                        }
                    } else {
                        copyProperty(dest, name, value);
                    }
                }
            }
        } else {
            PropertyDescriptor[] origDescriptors = getPropertyUtils().getPropertyDescriptors(src);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (getPropertyUtils().isReadable(src, name) &&
                        getPropertyUtils().isWriteable(dest, name)) {
                    try {
                        Object value = getPropertyUtils().getSimpleProperty(src, name);
                        if (ignoreNullFlag) {
                            if (value != null) {
                                copyProperty(dest, name, value);
                            }
                        } else {
                            copyProperty(dest, name, value);
                        }
                    } catch (NoSuchMethodException e) {
                        // Should not happen
                    }
                }
            }
        }
    }
}
