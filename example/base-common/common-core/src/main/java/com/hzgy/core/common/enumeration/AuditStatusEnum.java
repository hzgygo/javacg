package com.hzgy.core.common.enumeration;

public enum AuditStatusEnum implements BaseEnum{

    NO_AUDIT(0,"无需审核"),

    AUDIT(1,"待审核"),

    AUDITED(2,"已审核");

    private int typeId;

    private String typeName;

    AuditStatusEnum(int typeId, String typeName){
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
        AuditStatusEnum[] enums = values();
        for (AuditStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        AuditStatusEnum[] enums = values();
        for (AuditStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
