package com.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.config.CommonConfig;
import com.zhq.util.AsposeUtil;
import com.zhq.util.IOUtil.IOUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * AsposeUtil 类的 Demo
 * @author Eddie Zhang
 */
public class AsposeUtil_Demo {
    public static void main(String[] args) {
        AsposeUtil_Demo instance = new AsposeUtil_Demo();

//        instance.demo1();
//        instance.demo2();
        instance.demo3();
    }

    /**
     * demo : fillWordFields
     */
    public void demo1() {
        try {
            File docFile = new File(CommonConfig.DEMO_FILE_LOCATION + "aspose/aspose_demo_1.docx");
            Document doc = new Document(docFile.getAbsolutePath());
            JSONObject data = new JSONObject();

            data.put("interface_name", "interface_name_test1");
            data.put("interface_url", "www.baidu.com");
            data.put("interface_description", "This is a description of the ");
            data.put("interface_reqdata", "");
            data.put("interface_retdata", "");
            data.put("interface_pic", "");

            AsposeUtil.fillWordFields(doc, data);
            doc.save(CommonConfig.DEMO_FILE_LOCATION + "aspose/out/aspose_demo_1_out.docx", SaveFormat.DOCX);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * demo : fillWordTable
     */
    public void demo2() {
        try {
            File docFile = new File(CommonConfig.DEMO_FILE_LOCATION + "/aspose/aspose_demo_2.docx");
            Document doc = new Document(docFile.getAbsolutePath());
            List<String> colList = new ArrayList<String>();
            colList.add("interface_name");
            colList.add("interface_url");
            colList.add("interface_description");
            colList.add("interface_reqdata");
            colList.add("interface_retdata");
            colList.add("interface_pic");

            JSONArray dataArray = new JSONArray();
            for (int i = 0; i < 5; i++) {
                JSONObject rowData = new JSONObject();

                rowData.put("interface_name", "interface_Name " + i);
                rowData.put("interface_url", "url_Name " + i);
                rowData.put("interface_description", "description " + i);
                rowData.put("interface_reqdata", "reqDataContent " + i);
                rowData.put("interface_retdata", "retDataContent " + i);
                rowData.put("interface_pic", "PicContent " + i);

                dataArray.add(rowData);
            }

            AsposeUtil.fillWordTable(doc, "interface", colList, dataArray);
            doc.save(CommonConfig.DEMO_FILE_LOCATION + "aspose/out/aspose_demo_2_out.docx", SaveFormat.DOCX);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * demo : toPdf
     */
    public void demo3() {
        File wordFile = new File(CommonConfig.DEMO_FILE_LOCATION + "aspose/aspose_demo_3.docx");
        File pdfFile = new File(CommonConfig.DEMO_FILE_LOCATION + "aspose/out/aspose_demo_3_out.pdf");

        File tmpFile = AsposeUtil.toPdf(wordFile);
        IOUtil.copyFile(tmpFile, pdfFile);
        tmpFile.delete();
    }
}
