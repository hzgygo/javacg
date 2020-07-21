package com.hzgy.core.common.enumeration;

public enum EventCodeRuleEnum implements BaseEnum {

    BO(0,"对象"),

    RELA(3,"关系"),

    INDICATOR(7,"指标");

    private int typeId;

    private String typeName;

    EventCodeRuleEnum(int typeId, String typeName){
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
        EventCodeRuleEnum[] enums = values();
        for (EventCodeRuleEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        EventCodeRuleEnum[] enums = values();
        for (EventCodeRuleEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
