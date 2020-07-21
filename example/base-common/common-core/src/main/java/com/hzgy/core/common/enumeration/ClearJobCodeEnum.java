package com.hzgy.core.common.enumeration;

public enum ClearJobCodeEnum {

    DATA_CHECK("data_check","数据检查"),

    DATA_COMPARE("data_compare","数据比对"),

    DATA_CORRECT("data_correct","数据修正"),

    DATA_AUDIT("data_audit","数据审核"),

    DATA_CONFIRM("data_confirm","数据确认"),

    DATA_EXCEPTION("data_exception","数据异常");

    private String typeId;

    private String typeName;

    ClearJobCodeEnum(String typeId, String typeName){
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public static boolean isContain(String typeId) {
        ClearJobCodeEnum[] enums = values();
        for (ClearJobCodeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(String typeId) {
        ClearJobCodeEnum[] enums = values();
        for (ClearJobCodeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
