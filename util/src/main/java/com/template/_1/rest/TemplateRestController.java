package com.template._1.rest;

import com.zhq.util.JsonUtil.FastjsonUtil;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模板代码：Rest 接口控制器的模板类
 */
@RestController
@RequestMapping("template")
public class TemplateRestController {


    @RequestMapping(value = "templateHandlerMethod", method = RequestMethod.POST)
    public String templateHandlerMethod(@RequestBody String params) {
        try {


            return FastjsonUtil.jsonRestReturn("200", "执行成功", "");
        }
        catch (Exception e) {
            e.printStackTrace();
            return FastjsonUtil.jsonRestReturn("500", "执行错误", "");
        }
    }
}
