package com.tool.interface_doc_generator.config;

public class IConfig_3 {
    // 接口名称
    public static final String NAME = "事项分类列表";
    // 接口的访问路径
    public static final String URL = "http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/getTaskTypeList";
    // 接口调用方式 ( POST, GET )
    public static final String METHOD = "POST";
    // 请求内容类型
    public static final String CONTENT_TYPE = "application/json;charset=utf-8;";
    // 可结束类型
    public static final String ACCEPT = "json;";
    // 接口所需入参
    public static final String INPUT() {
        return "{\n" +
                "    \"token\": \"验证规则码\",\n" +
                "    \"params\": {\n" +
                "        // 中心guid\n" +
                "        \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\", \n" +
                "        // 大厅guid，如果想获取一个中心所有部门，传all\n" +
                "        \"hallguid\": \"all\", \n" +
                "        // 部门guid，如果是针对大厅查找事项的，ouguid传空。\n" +
                "        \"ouguid\": \"\", \n" +
                "        \"currentpage\": 0,\n" +
                "        \"pagesize\": 2\n" +
                "    }\n" +
                "}";
    }
    // 接口得到回参格式
    public static final String OUTPUT() {
        return  "{\n" +
                "    \"custom\": {\n" +
                "        \"totalcount\": \"80\",\n" +
                "        \"code\": 1,\n" +
                "        \"tasktypelist\": [\n" +
                "            {\n" +
                "                \"tasktypename\": \"医保综合业务\",\n" +
                "                \"isface\": \"0\",\n" +
                "                \"taskwaitnum\": \"0\",\n" +
                "                \"tasktypeguid\": \"b0949462-46cd-460c-b5c8-8c94633f7016\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"tasktypename\": \"医保对公业务\",\n" +
                "                \"isface\": \"0\",\n" +
                "                \"taskwaitnum\": \"0\",\n" +
                "                \"tasktypeguid\": \"1fa1bcf4-7ab1-46a7-bf5b-21375f27566e\"\n" +
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
    public static final String DESCRIPTION = "根据大厅，中心，部门获取对应的事项分类列表";
}
