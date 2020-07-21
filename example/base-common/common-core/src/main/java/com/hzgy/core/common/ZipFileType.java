package com.hzgy.core.common;


public enum ZipFileType {

    RAR("rar", 1),
    ZIP("zip", 2),
    Z7("7z", 3),
    GZ("gz", 4);

    /**
     * 枚举名称
     */
    private String name;

    /**
     * 返回码
     */
    private Integer type;

    // 构造方法
    private ZipFileType(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
