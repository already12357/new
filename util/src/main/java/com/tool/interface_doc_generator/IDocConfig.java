package com.tool.interface_doc_generator;

import com.alibaba.fastjson.JSONObject;
import com.zhq.util.JsonUtil.FastjsonUtil;
import com.zhq.util.JsonUtil.JsonUtil;

/**
 * 接口的配置信息, 记录了通用的一些接口配置信息
 * 注 : 如需个性化，请从 config 包中配置
 */
public class IDocConfig {
    // 接口名称
    public static final String NAME = "接口文档测试名称";
    // 接口的访问路径
    public static final String URL = "http://localhost:8088/mockwebLink/myLink?web=44fdf1";
    // 接口调用方式 ( POST, GET )
    public static final String METHOD = "POST";
    // 请求内容类型
    public static final String CONTENT_TYPE = "application/json;charset=utf-8;";
    // 可结束类型
    public static final String ACCEPT = "json;";
    // 接口所需入参
    public static final String INPUT() {
        JSONObject dataJson = new JSONObject();
        JSONObject paramObject = new JSONObject();

        paramObject.put("idcard", "333");
        paramObject.put("pageNumber", 1);
        paramObject.put("pageSize", 4);
        dataJson.put("params", paramObject);

        return dataJson.toJSONString();
    }
    // 接口得到回参格式
    public static final String OUTPUT() {
        JSONObject dataJson = new JSONObject();

        dataJson.put("grzh", 121);
        dataJson.put("results", "helow");

        return FastjsonUtil.jsonRestReturn("200", "Success", dataJson.toJSONString());
    }
}
