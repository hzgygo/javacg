package com.hzgy.core.common.constant;

import java.util.Arrays;
import java.util.List;

public class EventDescConstant {
    private EventDescConstant(){}

    public static final List<String> EVENT_DESC_LIST = Arrays.asList(
            EventDescConstant.M_BO_CODE,
            EventDescConstant.S_BO_CATEGORY,
            EventDescConstant.M_BO_NAME,
            EventDescConstant.S_BO_CODE,
            EventDescConstant.M_BO_CATEGORY,
            EventDescConstant.S_BO_NAME,
            EventDescConstant.RELA_TYPE,
            EventDescConstant.STAT_PERIOD,
            EventDescConstant.ISSUE_DT,
            EventDescConstant.MONEY_TYPE,
            EventDescConstant.SOURCE,
            EventDescConstant.BIZ_DATE,
            EventDescConstant.INDICATOR_CLASS1,
            EventDescConstant.INDICATOR_CLASS2,
            EventDescConstant.INDICATOR_CLASS3,
            EventDescConstant.INDICATOR_CLASS4,
            EventDescConstant.INDICATOR_CODE,
            EventDescConstant.INDICATOR_DATE);

    /** 主对象识别码 */
    public static final String M_BO_CODE = "m_bo_code";

    /** 主对象类目 */
    public static final String M_BO_CATEGORY = "m_bo_category";

    /** 主对象名称 */
    public static final String M_BO_NAME = "m_bo_name";

    /** 从对象识别码 */
    public static final String S_BO_CODE = "s_bo_code";

    /** 从对象类目 */
    public static final String S_BO_CATEGORY = "s_bo_category";

    /** 从对象名称  */
    public static final String S_BO_NAME = "s_bo_name";

    /** 关系类型 */
    public static final String RELA_TYPE = "rela_type";

    /** 统计期间 */
    public static final String STAT_PERIOD = "stat_period";

    /** 发布时间字段 */
    public static final String ISSUE_DT = "issue_dt";

    /** 币种 */
    public static final String MONEY_TYPE = "money_type";

    /** 来源 */
    public static final String SOURCE = "source";

    /** 业务时期 */
    public static final String BIZ_DATE = "biz_date";


    /** 指标一级分类 */
    public static final String INDICATOR_CLASS1 = "indicator_class1";
    /** 指标二级分类 */
    public static final String INDICATOR_CLASS2 = "indicator_class2";
    /** 指标三级分类 */
    public static final String INDICATOR_CLASS3 = "indicator_class3";
    /** 指标四级分类 */
    public static final String INDICATOR_CLASS4 = "indicator_class4";
    /** 指标识别码 */
    public static final String INDICATOR_CODE = "indicator_code";
    /** 指标日期 */
    public static final String INDICATOR_DATE = "indicator_date";
}
