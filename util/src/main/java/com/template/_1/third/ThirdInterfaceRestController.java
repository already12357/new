package com.template._1.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhq.util.HttpUtil.HttpUtil;
import com.zhq.util.JsonUtil.FastjsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 第三方接口对接的控制器模板
 * @author Eddie Zhang
 */
public class ThirdInterfaceRestController {
    /**
     * 接口 (1) [ 变量名 NAME1 ]
     */
    /**
     * 1. 根据前端传入的参数构造请求数据 ( requestData_[***] ), 以函数形式返回 json 格式请求字符串
     */
    private String requestData_name1(JSONObject obj) {
        JSONObject requestObject = new JSONObject();
        /**
         * 解析所需参数
         * String param1 = obj.getString("param1");
         * Integer param2 = obj.getInteger("param2");
         * ....
         */
        /**
         * 处理加工请求参数, 返回在 requestObject 中
         */
        return requestObject.toJSONString();
    }

    /**
     * 2. 使用生成的接口参数，进行具体的 Http 请求
     */
    private String doRequest_name1(String requestParams) {
        return HttpUtil.post(ThirdConstant.URL_NAME1, requestParams);
    }

    /**
     * 3. 处理接口的响应数据 (dataProcess_[***]) , 传入响应的 json 字符串, 返回处理后的需要响应的信息
     */
    private JSONObject dataProcess_name1(String responseStr) {
        JSONObject resBody = JSON.parseObject(responseStr);
        JSONObject dataJson = new JSONObject();

        /**
         * 响应数据处理, 放入 dataJson 中
         */

        return dataJson;
    }

    /**
     * 请求第三方接口的总方法 ( [***]RestController )
     * @return
     */
    @RequestMapping(value = "name1RestController")
    public String name1RestController(String params) {
        JSONObject json = JSON.parseObject(params);
        JSONObject obj = (JSONObject) json.get("params");
        // 1. 生成请求参数
        String requestParams = requestData_name1(obj);

        try {
            // 2. 执行具体的 Http 请求
            String responseStr = doRequest_name1(requestParams);

            // 3. 处理响应数据，生成返回结果
            JSONObject dataJson = dataProcess_name1(responseStr);

            /**
             * 开发环境使用 :
             * return JsonUtils.zwdtRestReturn("0", "接口调用异常", e.getMessage());
             */
            return FastjsonUtil.jsonRestReturn("200", "执行成功", dataJson.toJSONString());
        }
        catch (Exception e) {
            e.printStackTrace();

            /**
             * 开发环境使用 :
             * return JsonUtils.zwdtRestReturn("0", "接口调用异常", e.getMessage());
             */
            return FastjsonUtil.jsonRestReturn("500", "执行错误", e.getMessage());
        }
    }







    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!
     * 复制
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    /**
     * 接口 (2) [ 变量名 NAME2 ]
     * 用于复制
     */
    private String requestData_name2(JSONObject obj) {
        JSONObject requestObject = new JSONObject();
        /**
         * 解析所需参数
         * String param1 = obj.getString("param1");
         * Integer param2 = obj.getInteger("param2");
         * ....
         */
        /**
         * 处理加工请求参数, 返回在 requestObject 中
         */
        return requestObject.toJSONString();
    }
    private String doRequest_name2(String requestParams) {
        return HttpUtil.post(ThirdConstant.URL_NAME1, requestParams);
    }
    private JSONObject dataProcess_name2(String responseStr) {
        JSONObject resBody = JSON.parseObject(responseStr);
        JSONObject dataJson = new JSONObject();

        /**
         * 响应数据处理, 放入 dataJson 中
         */

        return dataJson;
    }

    @RequestMapping(value = "name2RestController")
    public String name2RestController(String params) {
        JSONObject json = JSON.parseObject(params);
        JSONObject obj = (JSONObject) json.get("params");
        // 1. 生成请求参数
        String requestParams = requestData_name2(obj);

        try {
            // 2. 执行具体的 Http 请求
            String responseStr = doRequest_name2(requestParams);

            // 3. 处理响应数据，生成返回结果
            JSONObject dataJson = dataProcess_name2(responseStr);


            /**
             * 开发环境使用 :
             * return JsonUtils.zwdtRestReturn("200", "执行成功", dataJson.toJSONString());
             */
            return FastjsonUtil.jsonRestReturn("200", "执行成功", dataJson.toJSONString());
        }
        catch (Exception e) {
            e.printStackTrace();

            /**
             * 开发环境使用 :
             * return JsonUtils.zwdtRestReturn("0", "接口调用异常", e.getMessage());
             */
            return FastjsonUtil.jsonRestReturn("500", "执行错误", e.getMessage());
        }
    }

}
