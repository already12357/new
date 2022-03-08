package com.tool.interface_doc_generator.config;

/**
 * 接口-1 的个性化配置信息
 */
public class IConfig_1 extends AbstractConfig {
//    // 接口名称
//    public static final String NAME = "大厅列表";
//    // 接口的访问路径
//    public static final String URL = "http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/getHallList";
//    // 接口调用方式 ( POST, GET )
//    public static final String METHOD = "POST";
//    // 请求内容类型
//    public static final String CONTENT_TYPE = "application/json;charset=utf-8;";
//    // 可结束类型
//    public static final String ACCEPT = "json;";
    // 接口所需入参
    public String INPUT() {
        return "{\n" +
                "    \"token\": \"\",\n" +
                "    \"params\": {\n" +
                "        \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\"\n" +
                "    }\n" +
                "}";
    }
    // IConfig_3
    public String OUTPUT() {
        return "{\n" +
                "    \"custom\": {\n" +
                "        \"halllist\": [\n" +
                "            {\n" +
                "                \"hallname\": \"所有大厅\",\n" +
                "                \"rowguid\": \"all\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"hallname\": \"三楼大厅\",\n" +
                "                \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "                \"yearflag\": \"\",\n" +
                "                \"operateusername\": \"\",\n" +
                "                \"ordernum\": 1,\n" +
                "                \"id\": \"\",\n" +
                "                \"row_id\": \"\",\n" +
                "                \"floor\": \"3\",\n" +
                "                \"belongxiaqucode\": \"\",\n" +
                "                \"rowguid\": \"e789f3ba-8506-46c4-af1a-ba5f19ab96e6\",\n" +
                "                \"operatedate\": 1640656071000\n" +
                "            },\n" +
                "            {\n" +
                "                \"hallname\": \"二楼大厅\",\n" +
                "                \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "                \"yearflag\": \"\",\n" +
                "                \"operateusername\": \"\",\n" +
                "                \"ordernum\": 1,\n" +
                "                \"id\": \"\",\n" +
                "                \"row_id\": \"\",\n" +
                "                \"floor\": \"2\",\n" +
                "                \"belongxiaqucode\": \"\",\n" +
                "                \"rowguid\": \"efc976f8-8a1b-4091-8046-91a0e8e9b713\",\n" +
                "                \"operatedate\": 1640656071000\n" +
                "            },\n" +
                "            {\n" +
                "                \"hallname\": \"一楼大厅\",\n" +
                "                \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "                \"yearflag\": \"\",\n" +
                "                \"operateusername\": \"\",\n" +
                "                \"ordernum\": 0,\n" +
                "                \"id\": \"\",\n" +
                "                \"row_id\": \"\",\n" +
                "                \"floor\": \"1\",\n" +
                "                \"belongxiaqucode\": \"\",\n" +
                "                \"rowguid\": \"ecdaaf59-ee9a-482e-b99e-35625fe85bbd\",\n" +
                "                \"operatedate\": 1640656071000\n" +
                "            }\n" +
                "        ],\n" +
                "        \"code\": 1,\n" +
                "        \"text\": \"\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"text\": \"\"\n" +
                "    }\n" +
                "}";
    }

    public String NAME() {
        return "";
    }

    public String URL() {
        return "http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/getHallList";
    }

    public String METHOD() {
        return "POST";
    }

    public String CONTENT_TYPE() {
        return "application/json;charset=utf-8;";
    }

    public String ACCEPT() {
        return "json;";
    }

    public String DESCRIPTION() {
        return "获取当前中心下取号机所在大厅名称, Mac 地址不传, 可获取所有大厅列表";
    }
}
