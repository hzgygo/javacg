package com.hzgy.core.common.enumeration;

public enum AttrTypeEnum implements BaseEnum {

    NORMAL_ATTR(0,"一般属性"),

    DERIVE_ATTR(1,"派生属性");

    private int typeId;

    private String typeName;

    AttrTypeEnum(int typeId, String typeName){
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
        AttrTypeEnum[] enums = values();
        for (AttrTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        AttrTypeEnum[] enums = values();
        for (AttrTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
