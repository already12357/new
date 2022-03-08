package com.tool.interface_doc_generator.config;

public class IConfig_7 {
//    // 接口名称
//    public static final String NAME = "取号列表";
//    // 接口的访问路径
//    public static final String URL = "http://localhost:8070/epoint-web-zwdt/rest/sfqueueonline/getQueueList";
//    // 接口调用方式 ( POST, GET )
//    public static final String METHOD = "POST";
//    // 请求内容类型
//    public static final String CONTENT_TYPE = "application/json;charset=utf-8;";
//    // 可结束类型
//    public static final String ACCEPT = "json;";
    // 接口所需入参
    public String INPUT() {
        return "{\n" +
                "    \"token\"  : \"\",\n" +
                "    \"params\" : {\n" +
                "        \"identitycardnum\" : \"510625197308120431\",\n" +
                "        \"type\" : \"0\",\n" +
                "        \"currentpage\" : \"0\",\n" +
                "        \"pagesize\" : \"2\"\n" +
                "    }\n" +
                "}";
    }
    // IConfig_3
    public String OUTPUT() {
        return "{\n" +
                "    \"custom\": {\n" +
                "        \"totalcount\": 3,\n" +
                "        \"code\": 1,\n" +
                "        \"queuelist\": [\n" +
                "            {\n" +
                "                \"queueguid\": \"5f5797aa-62e7-432f-90a4-1bbf5c7e1008\",\n" +
                "                \"tasktypename\": \"商品房网签合同业务、存量房网签合同业务、房屋交易备案查询\",\n" +
                "                \"qno\": \"F052\",\n" +
                "                \"statustext\": \"处理完成\",\n" +
                "                \"status\": \"2\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"queueguid\": \"222ce339-edff-4b2e-bab8-3419976edd0b\",\n" +
                "                \"tasktypename\": \"住宅专项维修资金交存\",\n" +
                "                \"qno\": \"F051\",\n" +
                "                \"statustext\": \"正在处理\",\n" +
                "                \"status\": \"1\"\n" +
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
    public static final String DESCRIPTION = "通过身份证查询用户的取号信息，可以通过不同的传参控制是否查询历史信息";
}
