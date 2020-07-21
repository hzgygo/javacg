package com.hzgy.core.common;


/**
 * 返回码枚举类型
 * 100 000-100 999 为系统保留，其他任何业务和模块不准使用
 * 101 000-199 999为个业务模块使用
 * 1：每个模块前 000-499 为前端输入错误范围码
 * 2：500-999位开发人员关注码范围，不对外暴露
 */
public enum ReturnCode {

    SUCCESS("成功", 100000),
    FAIL("失败", 100001),
    PARAM_ERROR("参数错误", 100002),
    LOGINOUT_ERROR("Token信息不存在，退出登录失败", 100993),
    ACCOUNT_ERROR("账户不存在", 100994),
    ACCOUNT_PWD_ERROR("账户密码错误", 100995),
    DUPLICATE("设置的代码已经存在，不能重复添加", 100996),
    UNLOGIN("未登录", 100997),
    UNAYTHORIZED("无权访问", 100998),
    SYSTEM_ERROR("系统异常错误", 100999);


    /**
     * 枚举名称
     */
    private String name;

    /**
     * 返回码
     */
    private Integer code;

    // 构造方法
    private ReturnCode(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
