package com.tool.interface_doc_generator.config;

public class IConfig_5 {
    // 接口名称
    public static final String NAME = "取号列表";
    // 接口的访问路径
    public static final String URL = "http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/getQno";
    // 接口调用方式 ( POST, GET )
    public static final String METHOD = "POST";
    // 请求内容类型
    public static final String CONTENT_TYPE = "application/json;charset=utf-8;";
    // 可结束类型
    public static final String ACCEPT = "json;";
    // 接口所需入参
    public static final String INPUT() {
        return "{\n" +
                "    \"token\" : \"验证规则码\",\n" +
                "    \"params\" : {\n" +
                "        \"cardno\" : \"320582199905105710\",\n" +
                "        \"phone\" : \"13913296925\",\n" +
                "        \"centerguid\" : \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "        \"tasktypeguid\" : \"ab58e6c7-40c9-4ce7-98ba-9261023e36c6\",\n" +
                "        \"macaddress\" : \"c4:00:ad:b0:28:83\",\n" +
                "        \"islove\":\"\"\n" +
                "        \n" +
                "    }\n" +
                "}";
    }
    // 接口得到回参格式
    public static final String OUTPUT() {
        return  "{\n" +
                "    \"custom\": {\n" +
                "        \"queueguid\": \"7d360253-1bd3-48d4-b343-f341bac89204\",\n" +
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
    public static final String DESCRIPTION = "取号方法";
}
