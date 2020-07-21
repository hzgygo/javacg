package com.hzgy.core.common;


public enum FileType {

    MP4("MP4", 1),
    OGG3("失败", 2),
    WEBM("WEBM", 3),
    MP3("MP3", 1),
    WAV("WAV", 3),
    JPG("JPG", 1),
    PNG("PNG", 2),
    GIF("PNG", 3);


    /**
     * 枚举名称
     */
    private String name;

    /**
     * 返回码
     */
    private Integer type;

    // 构造方法
    private FileType(String name, Integer type) {
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
