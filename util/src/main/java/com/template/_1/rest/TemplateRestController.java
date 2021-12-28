package com.template._1.rest;

import com.template._1.service.api.ITemplateService;
import com.zhq.util.JsonUtil.FastjsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模板代码：Rest 接口控制器的模板类
 *
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
    @RequestMapping(value = "templateHandlerMethod", method = RequestMethod.POST)
    public String templateHandlerMethod(@RequestBody String params) {
        try {
            /**
             * 执行接口处理逻辑
             */

            // 将返回数据放置在特定格式的 json 中返回
            return FastjsonUtil.jsonRestReturn("200", "执行成功", "success");
        }
        catch (Exception e) {
            e.printStackTrace();
            return FastjsonUtil.jsonRestReturn("500", "执行错误", "error");
        }
    }
}
