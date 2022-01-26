package com.template._1.util;

import com.template._1.third.ThirdConstant;
import com.zhq.util.AsposeUtil;
import com.zhq.util.IOUtil.IOUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.*;

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
     * 接口请求成功的图片位置 ( 默认位于桌面 )
     */
    public static final String IMG_LOCATION = FileSystemView.getFileSystemView().getHomeDirectory() + "/copyFile.png";

    /**
     * 根据对应接口，生成对应的接口文档, 替换对应的域,
     * 可以使用 ThirdConstant 中的常量
     */
    public static void interfaceDocument() {
        List<Map<String, Object>> docsFieldsMapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> docFieldsMap = new HashMap<String, Object>();

        /**
         * 根据 ThirdConstant 中定义的常量直接赋值，
         * 使用 Aspose 直接替换模板中的域
         */
        docFieldsMap.put("interface_name", ThirdConstant.CHI_NAME1);
        docFieldsMap.put("interface_url", ThirdConstant.URL_NAME1);
        docFieldsMap.put("interface_description", ThirdConstant.DESCRIPTION_NAME1);
        docFieldsMap.put("interface_reqdata", ThirdConstant.TEST_REQ_DATA_NAME1());
        docFieldsMap.put("interface_retdata", ThirdConstant.TEST_RES_DATA_NAME1());
        docFieldsMap.put("interface_pic", Base64.getEncoder().encode(IOUtil.bytesInFile(new File(IMG_LOCATION))));

        docsFieldsMapList.add(docFieldsMap);

        File templateDoc = new File(TEMPLATE_LOCATION);
        File renderDoc = new File(RENDER_LOCATION);
        AsposeUtil.replaceRegionFieldsToDoc(templateDoc, renderDoc, docsFieldsMapList, "interface");
    }


    /**
     * 在类内部直接调用对应的接口内容，用于事项对应的效果
     * @param args
     */
    public static void main(String[] args) {
        interfaceDocument();
    }
}
