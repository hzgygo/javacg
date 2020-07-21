package com.hzgy.core.common.enumeration;

public enum TimeSeriesStyleEnum {

    /*
    AST Style (Attribute-Symbol-Time) 主要用于获取已有数据，如因子、连续合约、指数行情、基金净值等。
    SAT Style (Symbol-Attribute-Time) 主要用于多属性运算，如使用量价信息计算指标，使用行情财务计算因子等。
    TAS Style (Time-Attribute-Symbol) 主要用于获取横截面数据，进而进行其他数据处理。
    */

    AST, SAT, TAS;


}
