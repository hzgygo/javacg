package com.hzgy.core.common;

/**
 * 数据源类型枚举类型
 */
public enum DataSourceType {

    read("read", "cluster"),
    write("write", "master");

    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
