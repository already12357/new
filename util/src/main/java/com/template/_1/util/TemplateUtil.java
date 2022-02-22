package com.template._1.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.template._1.third.ThirdConstant;
import com.zhq.util.AsposeUtil;
import com.zhq.util.IOUtil.IOUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.*;

/**
 * 代码模板的帮助类,
 * 自动生成对应的接口文档
 * 接口文档配置类 --> 复制 --> 运行 main
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
        List<String> colList = new ArrayList<>();
        colList.add("interface_name");
        colList.add("interface_url");
        colList.add("interface_description");
        colList.add("interface_reqdata");
        colList.add("interface_retdata");
        colList.add("interface_pic");

        JSONArray data = new JSONArray();
        JSONObject rowData = new JSONObject();
        rowData.put("interface_name", ThirdConstant.CHI_NAME1);
        rowData.put("interface_url", ThirdConstant.URL_NAME1);
        rowData.put("interface_description", ThirdConstant.DESCRIPTION_NAME1);
        rowData.put("interface_reqdata", ThirdConstant.TEST_REQ_DATA_NAME1());
        rowData.put("interface_retdata", ThirdConstant.TEST_RES_DATA_NAME1());
        rowData.put("interface_pic", Base64.getEncoder().encode(IOUtil.bytesInFile(new File(IMG_LOCATION))));
        data.add(rowData);

        try {
            File templateFile = new File(TEMPLATE_LOCATION);
            Document templateDoc = new Document(templateFile.getAbsolutePath());
            AsposeUtil.fillWordTable(templateDoc, "interface", colList, data);

            File renderDoc = new File(RENDER_LOCATION);
            templateDoc.save(renderDoc.getAbsolutePath(), SaveFormat.DOCX);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 在类内部直接调用对应的接口内容，用于事项对应的效果
     * @param args
     */
    public static void main(String[] args) {
        interfaceDocument();
    }
}
