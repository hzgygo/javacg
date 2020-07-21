package com.hzgy.core.common.enumeration;

public enum FileTypeEnum implements BaseEnum {

    ETL(1,"ETL文件"),

    ATTR_DESC(2,"属性定义"),

    DATA_SOURCE(3,"数据源"),

    ATTR_CATEGORY(4,"属性类目"),

    ATTR_CATEGORY_MAP_ATTR(5,"属性类目-属性映射");

    private int typeId;

    private String typeName;

    FileTypeEnum(int typeId, String typeName){
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
        FileTypeEnum[] enums = values();
        for (FileTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        FileTypeEnum[] enums = values();
        for (FileTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
