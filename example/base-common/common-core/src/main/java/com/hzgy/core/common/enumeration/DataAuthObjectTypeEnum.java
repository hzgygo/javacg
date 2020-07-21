package com.hzgy.core.common.enumeration;

/** 数据权限对象类型 */
public enum DataAuthObjectTypeEnum implements BaseEnum {

    BO_CODE_TYPE(1,"对象编码类型"),
    BO_CATEGORY(2,"对象类目"),
    ATTR_CATEGORY(3,"属性类目");

    private int typeId;

    private String typeName;

    DataAuthObjectTypeEnum(int typeId, String typeName){
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public static boolean isContain(int typeId) {
        DataAuthObjectTypeEnum[] enums = values();
        for (DataAuthObjectTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        DataAuthObjectTypeEnum[] enums = values();
        for (DataAuthObjectTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
