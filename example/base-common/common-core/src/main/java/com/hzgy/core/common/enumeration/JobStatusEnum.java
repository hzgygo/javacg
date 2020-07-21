package com.hzgy.core.common.enumeration;

public enum JobStatusEnum implements BaseEnum {

    UNEXECUTED(0,"未执行"),

    EXECUTED(1,"已执行");

    private int typeId;

    private String typeName;

    JobStatusEnum(int typeId, String typeName){
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
        JobStatusEnum[] enums = values();
        for (JobStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        JobStatusEnum[] enums = values();
        for (JobStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
