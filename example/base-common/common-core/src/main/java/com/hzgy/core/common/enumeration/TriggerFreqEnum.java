package com.hzgy.core.common.enumeration;

/** 触发频率 */
public enum TriggerFreqEnum implements BaseEnum {

    TRIGGER_NO(0,"不触发"),
    TRIGGER_DD(1,"日触发"),
    TRIGGER_MM(2,"月触发"),
    TRIGGER_QQ(3,"季触发"),
    TRIGGER_YY(4,"年触发");

    private int typeId;

    private String typeName;

    TriggerFreqEnum(int typeId, String typeName){
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
        TriggerFreqEnum[] enums = values();
        for (TriggerFreqEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        TriggerFreqEnum[] enums = values();
        for (TriggerFreqEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
