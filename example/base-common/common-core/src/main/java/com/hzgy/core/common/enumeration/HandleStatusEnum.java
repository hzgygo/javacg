package com.hzgy.core.common.enumeration;

public enum HandleStatusEnum implements BaseEnum {

    IMPORT_TEMP_ING(1,"正在处理"),// 正在导入临时表
    IMPORT_TEMP_FAIL(2,"处理失败"),// 导入临时表失败
    IMPORT_TEMP_SUCCESS(3,"正在处理"),// 导入临时表成功
    VERIFY_ING(4,"正在处理"),// 正在验证
    VERIFY_FAIL(5,"处理失败"),// 验证失败
    VERIFY_SUCCESS(6,"正在处理"),// 验证成功
    IMPORT_ING(7,"正在处理"),// 正在导入正式表
    IMPORT_FAIL(8,"处理失败"),// 导入正式表失败
    IMPORT_SUCCESS(9,"处理成功");// 导入正式表成功

    private int typeId;

    private String typeName;

    HandleStatusEnum(int typeId, String typeName){
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
        HandleStatusEnum[] enums = values();
        for (HandleStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        HandleStatusEnum[] enums = values();
        for (HandleStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
