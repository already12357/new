package com.template._1.service.impl;

import com.template._1.service.api.ITemplateService;
import org.springframework.stereotype.Component;

/**
 * 服务类接口的实现类
 * 1. 注意在实现类上标注 @Component 将接口注入到 IOC 中
 * 2. 在实现类中，实际使用内部的 Service 类的同名方法实现具体的逻辑
 */
@Component
public class TemplateServiceImpl implements ITemplateService {

    @Override
    public String interfaceMethod1(String param1, int param2, boolean param3) {
        // 内部的实际执行逻辑由自定义的 TemplateService 类 中的 同名方法实现
        return new TemplateService().interfaceMethod1(param1, param2, param3);
    }

    @Override
    public boolean interfaceMethod2(long param1, char param2) {
        return new TemplateService().interfaceMethod2(param1, param2);
    }
}
