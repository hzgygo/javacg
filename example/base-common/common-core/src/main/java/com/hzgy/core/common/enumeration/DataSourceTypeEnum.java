package com.hzgy.core.common.enumeration;

public enum DataSourceTypeEnum {

    MYSQL(0,"com.mysql.jdbc.Driver"),
    ORACLE  (1,"oracle.jdbc.driver.OracleDriver"),
    SQLSERVER(2,"com.microsoft.sqlserver.jdbc.SQLServerDriver");

    private int typeId;

    private String typeName;

    DataSourceTypeEnum(int typeId, String typeName){
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
        DataSourceTypeEnum[] enums = values();
        for (DataSourceTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        DataSourceTypeEnum[] enums = values();
        for (DataSourceTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
