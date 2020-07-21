package com.hzgy.core.common.enumeration;

public interface BaseEnum {
    int getTypeId();

    String getTypeName();

    static boolean isContain(int typeId) {
        return false;
    }
}
