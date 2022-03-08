package com.tool.interface_doc_generator.config;

/**
 * 接口 - 2 的个性化配置信息
 */
public class IConfig_2 {
    // 接口名称
    public static final String NAME = "部门列表";
    // 接口的访问路径
    public static final String URL = "http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/getOUList";
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
                "\t\t\"centerguid\" : \"ca38999e-dde6-4d63-a873-2115040e205a\", \n" +
                "\t\t\"hallguid\" : \"all\",\n" +
                "\t\t\"currentpage\" : 0,\n" +
                "\t\t\"pagesize\" : 2\n" +
                "\t}\n" +
                "}";
    }
    // 接口得到回参格式
    public static final String OUTPUT() {
        return  "{\n" +
                "    \"custom\": {\n" +
                "        \"totalcount\": \"11\",\n" +
                "        \"code\": 1,\n" +
                "        \"oulist\": [\n" +
                "            {\n" +
                "                \"ouguid\": \"a6db6eb4-a093-4688-a82e-9584baa107a1\",\n" +
                "                \"ougnum\": 0,\n" +
                "                \"ouname\": \"不动产中心\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ouguid\": \"36f2204c-e341-4e1f-9eeb-b73443e15709\",\n" +
                "                \"ougnum\": 0,\n" +
                "                \"ouname\": \"房地产交易中心\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"text\": \"\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"text\": \"\"\n" +
                "    }\n" +
                "}";
    }
    // 接口描述
    public static final String DESCRIPTION = "获取取号部门列表";
}
