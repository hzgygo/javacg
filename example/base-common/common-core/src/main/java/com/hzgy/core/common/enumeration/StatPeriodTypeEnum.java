package com.hzgy.core.common.enumeration;

/** 统计期间类型 */
public enum StatPeriodTypeEnum implements BaseEnum {

    UNCERTAIN(10,"不定时"),
    DAY_FREQUENCY(20,"日频"),
    WEEK_FREQUENCY(30,"周频"),
    MONTH_FREQUENCY(40,"月频"),
    QUARTER_FREQUENCY(50,"季频"),
    HALF_A_YEAR_FREQUENCY(60,"半年频"),
    YEAR_FREQUENCY(70,"年频"),
    HOUR_FREQUENCY(80,"时频"),
    MINUTE_FREQUENCY(81,"分频"),
    SECOND_FREQUENCY(82,"秒频");

    private int typeId;

    private String typeName;

    StatPeriodTypeEnum(int typeId, String typeName){
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
        StatPeriodTypeEnum[] enums = values();
        for (StatPeriodTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        StatPeriodTypeEnum[] enums = values();
        for (StatPeriodTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }

    public static StatPeriodTypeEnum getEnum(int typeId) {
        StatPeriodTypeEnum[] enums = values();
        for (StatPeriodTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration;
            }
        }
        return null;
    }
}
