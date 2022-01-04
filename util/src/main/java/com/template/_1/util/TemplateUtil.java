package com.template._1.util;

import java.lang.reflect.Method;

/**
 * 代码模板的帮助类,
 * 自动生成对应的接口文档
 */
public class TemplateUtil {
    /**
     * 接口模板的位置
     */
    public static final String TEMPLATE1_LOCATION = "classpath:file/template/_1/interface_doc1.docx";


    /**
     * 根据对应接口，生成对应的接口文档, 替换对应的域
     * 使用, appose 或 poi
     * @param restController 第三方控制器接口的名称
     * @param methodName 调用的接口方法名称
     */
    public static void interfaceDocument(Object restController, String methodName) {
        Class restClass = restController.getClass();
        Method interMethod = restClass.getMethod(methodName, );

    }

    /**
     * 在类内部直接调用对应的接口内容，用于事项对应的效果
     * @param args
     */
    public static void main(String[] args) {

    }
}
