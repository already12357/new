package com.template._1.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.template._1.service.api.ITemplateService;
import com.zhq.util.JsonUtil.FastjsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模板代码：Rest 接口控制器的模板类
 * @author Eddie Zhang
 */
@RestController
@RequestMapping("template")
public class TemplateRestController {
    /**
     * 使用 @Autowired 注入定义的 api 对象
     */
    @Autowired
    private ITemplateService templateService;

    /**
     * 1. 控制器中的处理器方法仅支持 POST 请求的映射，映射路径和方法名同名
     * 2. 使用 @RequestBody 标志入参，默认名为 params，json 格式字符串
     */
    @RequestMapping(value = "/templateHandlerMethod", method = RequestMethod.POST)
    public String templateHandlerMethod(@RequestBody String params) {
        // 解析传入的参数的固定格式 ( 通常变量名称不变，便于代码查看 )
        JSONObject json = JSON.parseObject(params);
        JSONObject obj = (JSONObject) json.get("params");

//        // 解析其中的参数
//        String param1 = obj.getString("param1");
//        Integer param2 = obj.getInteger("param2");
//        ....

        // 将返回的数据存放到 JSONObject 中
        JSONObject dataJson = new JSONObject();

        try {
            /**
             * 执行接口处理逻辑
             */

            // 将返回数据放置在特定格式的 json 中返回
            return FastjsonUtil.jsonRestReturn("200", "执行成功", dataJson.toJSONString());
        }
        catch (Exception e) {
            e.printStackTrace();
            // 异常时将异常信息返回到特定格式的 json 中
            return FastjsonUtil.jsonRestReturn("500", "执行错误", e.getMessage());
        }
    }


    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!
     * 复制
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    @RequestMapping(value = "/templateHandlerMethod2", method = RequestMethod.POST)
    public String templateHandlerMethod2(@RequestBody String params) {
        JSONObject json = JSON.parseObject(params);
        JSONObject obj = (JSONObject) json.get("params");

        /**
         * 解析所需参数
         * String param1 = obj.getString("param1");
         * Integer param2 = obj.getInteger("param2");
         * ....
         */

        JSONObject dataJson = new JSONObject();

        try {
            /**
             * 执行接口处理逻辑
             */


            /**
             * 开发环境使用 :
             * return JsonUtils.zwdtRestReturn("1", "接口调用成功！", dataJson);
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
