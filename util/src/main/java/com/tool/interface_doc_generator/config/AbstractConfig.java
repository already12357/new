package com.tool.interface_doc_generator.config;

/**
 * 抽象的配置类，用于配置文件继承
 */
public abstract class AbstractConfig {
    // 接口名称
    private String NAME = "";
    // 接口的访问路径
    private String URL = "";
    // 接口调用方式 ( POST, GET )
    private String METHOD = "POST";
    // 请求内容类型
    private String CONTENT_TYPE = "application/json;charset=utf-8;";
    // 可结束类型
    private String ACCEPT = "json;";
    // 接口所需入参
    private String INPUT = "";
    // IConfig_3
    private String OUTPUT = "";
    // 接口描述
    private String DESCRIPTION = "";

    public void setNAME(String NAME) {
        this.NAME = NAME();
    }
    public void setURL(String URL) {
        this.URL = URL();
    }
    public void setMETHOD(String METHOD) {
        this.METHOD = METHOD();
    }
    public void setCONTENT_TYPE(String CONTENT_TYPE) {
        this.CONTENT_TYPE = CONTENT_TYPE();
    }
    public void setACCEPT(String ACCEPT) {
        this.ACCEPT = ACCEPT();
    }
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION();
    }
    public void setINPUT(String INPUT) {
        this.INPUT = INPUT;
    }
    public void setOUTPUT(String OUTPUT) {
        this.OUTPUT = OUTPUT;
    }

    public String NAME() {
        return this.NAME;
    }
    public String URL() {
        return this.URL;
    }
    public String METHOD() {
        return this.METHOD;
    }
    public String CONTENT_TYPE() {
        return this.CONTENT_TYPE;
    }
    public String ACCEPT() {
        return this.ACCEPT;
    }
    public String DESCRIPTION() {
        return this.DESCRIPTION;
    }
    public String INPUT() {
        return this.INPUT;
    }
    public String OUTPUT() {
        return this.OUTPUT;
    }
}
