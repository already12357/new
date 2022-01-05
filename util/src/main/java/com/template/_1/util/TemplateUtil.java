package com.template._1.util;

import com.template._1.third.ThirdConstant;
import com.zhq.util.AsposeUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码模板的帮助类,
 * 自动生成对应的接口文档
 */
public class TemplateUtil {
    /**
     * 接口模板的位置
     */
    public static final String TEMPLATE_LOCATION = "src/main/resources/file/template/_1/interface_doc1.docx";

    /**
     * 渲染后的接口模板的生成位置 ( 默认桌面上 )
     */
    public static final String RENDER_LOCATION = "C:/Users/Administrator/Desktop/interface_test.docx";

    /**
     * 根据对应接口，生成对应的接口文档, 替换对应的域,
     * 可以使用 ThirdConstant 中的常量
     */
    public static void interfaceDocument() {
        Map<String, String> docFieldsMap = new HashMap<String, String>();

        /**
         * 根据 ThirdConstant 中定义的常量直接赋值，
         * 使用 Aspose 直接替换模板中的域
         */
        docFieldsMap.put("interface_name", ThirdConstant.CHI_NAME1);
        docFieldsMap.put("interface_url", ThirdConstant.URL_NAME1);
        docFieldsMap.put("interface_description", ThirdConstant.DESCRIPTION_NAME1);
        docFieldsMap.put("interface_reqdata", ThirdConstant.TEST_REQ_DATA_NAME1());
        docFieldsMap.put("interface_retdata", ThirdConstant.TEST_RES_DATA_NAME1());

        File templateDoc = new File(TEMPLATE_LOCATION);
        File renderDoc = new File(RENDER_LOCATION);
        AsposeUtil.replaceFieldsToDoc(templateDoc, renderDoc, docFieldsMap);
    }



    /**
     * 在类内部直接调用对应的接口内容，用于事项对应的效果
     * @param args
     */
    public static void main(String[] args) {
        interfaceDocument();
    }
}
