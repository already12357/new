package com.tool.interface_doc_generator.config;

public class IConfig_4 {
    // 接口名称
    public static final String NAME = "取号列表";
    // 接口的访问路径
    public static final String URL = "http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/checkTaskType";
    // 接口调用方式 ( POST, GET )
    public static final String METHOD = "POST";
    // 请求内容类型
    public static final String CONTENT_TYPE = "application/json;charset=utf-8;";
    // 可结束类型
    public static final String ACCEPT = "json;";
    // 接口所需入参
    public static final String INPUT() {
        return "{\n" +
                "\t\"token\" : \"\",\n" +
                "\t\"params\" : {\n" +
                "\t\t\"centerguid\" : \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "\t\t\"tasktypeguid\" : \"b0949462-46cd-460c-b5c8-8c94633f7016\",\n" +
                "\t\t\"macaddress\" : \"1111\"\n" +
                "    }\n" +
                "}";
    }
    // 接口得到回参格式
    public static final String OUTPUT() {
        return  "{\n" +
                "    \"custom\": {\n" +
                "        \"code\": 1,\n" +
                "        \"text\": \"\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"text\": \"\"\n" +
                "    }\n" +
                "}";
    }
    // 接口描述
    public static final String DESCRIPTION = "检查该事项分类是否可以取号";
}
