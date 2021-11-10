package com.zhq.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 一些与 Rest 返回规范有关的辅助类
 */
public class RestUtil {
    /**
     * Rest 接口返回带有状态码的信息
     * @param statCode 请求状态码, 通常使用网页的请求状态码 ( 默认为 200 )
     * @param attachMessage 附加的额外信息
     * @param data 返回的数据信息
     * @return json格式的数据
     * 如下格式 :
     * {
     *     # message 包含 返回状态码 和 返回的附加信息
     *     # data 返回的 json 数据字符串
     *     eg.
     *     "message" : {
     *         “status”:"200",
     *         "attachment":"请求成功"
     *     },
     *     “data” : "....."
     * }
     */
    public static String restReturnWithStat(String statCode, String attachMessage, String data) {
        JSONObject retObject = new JSONObject();
        JSONObject messageObject = new JSONObject();
        String inputData = "200";

        if (null != statCode && !statCode.equals("")) {
            inputData = statCode;
        }

        try {
            messageObject.put("status", inputData);
            messageObject.put("attachment", attachMessage);
            retObject.put("message", messageObject);
            retObject.put("data", data);
        }
        catch (Exception e) {
            e.printStackTrace();
            messageObject.put("status", "500");
            messageObject.put("attachment", "请求返回异常");
            retObject.put("message", messageObject);
            retObject.put("data", data);
        }

        return retObject.toJSONString();
    }
}
