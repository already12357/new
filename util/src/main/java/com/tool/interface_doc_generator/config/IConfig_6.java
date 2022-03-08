package com.tool.interface_doc_generator.config;

public class IConfig_6 {
    // 接口名称
    public static final String NAME = "取号详情";
    // 接口的访问路径
    public static final String URL = "http://localhost:8070/epoint-web-zwdt/rest/sfqueue/getQnoDetail";
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
                "        \"centerguid\" : \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "        \"queueguid\" : \"2195792b-7171-4ad7-8f0a-374a143d3584\"\n" +
                "    }\n" +
                "}";
    }
    // 接口得到回参格式
    public static final String OUTPUT() {
        return  "{\n" +
                "    \"custom\": {\n" +
                "        \"tasktypename\": \"退休审批\",\n" +
                "        \"note\": \"您好，办理时请主动出示此电子条形码，无需换取纸质小票。感谢您对我们的支持\",\n" +
                "        \"qno\": \"RS277\",\n" +
                "        \"code\": 1,\n" +
                "        \"windowno\": \"一楼48号;\",\n" +
                "        \"waitnum\": \"0\",\n" +
                "        \"text\": \"\",\n" +
                "        \"getnotime\": \"2022-01-07 15:46:29\",\n" +
                "        \"centername\": \"什邡市政务服务中心\",\n" +
                "        \"flownonum\": \"20220107001314\"\n" +
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
