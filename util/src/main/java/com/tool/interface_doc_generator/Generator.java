package com.tool.interface_doc_generator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.config.CommonConfig;
import com.tool.interface_doc_generator.config.*;
import com.zhq.util.AsposeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口文档生成器, 通过 AsposeUtil 替换 Word 中的文本域生成对应的接口文档
 * 主要修改 : TMP_LOCATION, CREATE_LOCATION, mergeFieldList, tmpData1, IDocConfig 与 IConfig_1 配置中的内容
 */
public class Generator {
    // 文件模板路径 ( 修改 )
    public static final String TMP_LOCATION = CommonConfig.INTERFACE_TMP_LOCATION + "interface_tmp1.docx";
    // 生成接口文件路径 ( 修改 )
    public static final String CREATE_LOCATION = CommonConfig.DESKTOP_LOCATION + "/interface.doc";

    /**
     * 生成对应的接口文件
     * @param renderData 渲染文件模板所需的对象
     * @param savePath 存储路径
     * @param more 更多的文档渲染数据
     */
    public void generateInterface_Doc(String savePath, JSONArray renderData, JSONArray...more) {
        try {
            // 设置文件模板路径
            File tmpDoc = new File(TMP_LOCATION);
            Document doc = new Document(tmpDoc.getAbsolutePath());
            JSONArray renderTotalData = new JSONArray();

            for (JSONArray docRenderDatas : more) {
                for (int i = 0; i < docRenderDatas.size(); i++) {
                    JSONObject docRenderData = docRenderDatas.getJSONObject(i);
                    renderTotalData.add(docRenderData);
                }
            }

            for (int j = 0; j < renderData.size(); j++) {
                JSONObject docRenderData2 = renderData.getJSONObject(j);
                renderTotalData.add(docRenderData2);
            }

            AsposeUtil.fillWordTable(doc, "interface", mergeFieldList(), renderTotalData);
            doc.save(savePath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成器运行入口, 运行该主函数来实现接口文件模板的渲染
     */
    public static void main(String[] args) {
//        Generator instance = new Generator();

        // 渲染 1, 2, 3....
//        instance.generateInterface_Doc(CommonConfig.DESKTOP_LOCATION + "/index-1.doc", instance.createRenderData(instance.config1()));
//        instance.generateInterface_Doc(CommonConfig.DESKTOP_LOCATION + "/index-2.doc", instance.createRenderData(instance.config2()));
//        instance.generateInterface_Doc(CommonConfig.DESKTOP_LOCATION + "/index-3.doc", instance.tmpData3());
//        instance.generateInterface_Doc(CommonConfig.DESKTOP_LOCATION + "/index-4.doc", instance.tmpData4());
//        instance.generateInterface_Doc(CommonConfig.DESKTOP_LOCATION + "/index-5.doc", instance.tmpData5());
//        instance.generateInterface_Doc(CommonConfig.DESKTOP_LOCATION + "/index-6.doc", instance.tmpData6());
//        instance.generateInterface_Doc(CommonConfig.DESKTOP_LOCATION + "/index-7.doc", instance.tmpData7());
        // .....

        for (int i = 0; i < 10; i++) {
            String hello = "" + i;
        }
    }


    /**
     * 文档中的编辑域名称 ( 《...》 ) 列表, 注意与之一一对应
     */
    public List<String> mergeFieldList() {
        List<String> colList = new ArrayList<>();

        colList.add("interface_name");
        colList.add("interface_url");
        colList.add("interface_description");
        colList.add("interface_reqdata");
        colList.add("interface_retdata");

        return colList;
    }


    /**
     * 根据配置文件对象生成对应的渲染数据
     * @param config 配置文件对象
     * @return
     */
    public JSONArray createRenderData(AbstractConfig config) {
        JSONArray dataRows = new JSONArray();

        // 有多接口时,添加多组 JSONObject 数据
        for (int i = 0; i < 1; i++) {
            JSONObject data = new JSONObject();

            data.put("interface_name", config.NAME());
            data.put("interface_url", config.URL());
            data.put("interface_reqdata", config.INPUT());
            data.put("interface_retdata", config.OUTPUT());
            // 单独的不通用的配置信息, 从单独的配置类中编写
            data.put("interface_description", config.DESCRIPTION());
            dataRows.add(data);
        }

        return dataRows;
    }


    /**
     * 生成对应的配置对象
     * @return
     */
    public AbstractConfig config1() {
        AbstractConfig config = new DocConfig();

        config.setNAME("大厅列表");
        config.setMETHOD("POST");
        config.setURL("http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/getHallList");
        config.setCONTENT_TYPE("application/json;charset=utf-8;");
        config.setACCEPT("json;");
        config.setDESCRIPTION("");
        config.setINPUT("{\n" +
                "    \"token\": \"\",\n" +
                "    \"params\": {\n" +
                "        \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\"\n" +
                "    }\n" +
                "}");
        config.setOUTPUT("{\n" +
                "    \"custom\": {\n" +
                "        \"halllist\": [\n" +
                "            {\n" +
                "                \"hallname\": \"所有大厅\",\n" +
                "                \"rowguid\": \"all\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"hallname\": \"三楼大厅\",\n" +
                "                \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "                \"yearflag\": \"\",\n" +
                "                \"operateusername\": \"\",\n" +
                "                \"ordernum\": 1,\n" +
                "                \"id\": \"\",\n" +
                "                \"row_id\": \"\",\n" +
                "                \"floor\": \"3\",\n" +
                "                \"belongxiaqucode\": \"\",\n" +
                "                \"rowguid\": \"e789f3ba-8506-46c4-af1a-ba5f19ab96e6\",\n" +
                "                \"operatedate\": 1640656071000\n" +
                "            },\n" +
                "            {\n" +
                "                \"hallname\": \"二楼大厅\",\n" +
                "                \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "                \"yearflag\": \"\",\n" +
                "                \"operateusername\": \"\",\n" +
                "                \"ordernum\": 1,\n" +
                "                \"id\": \"\",\n" +
                "                \"row_id\": \"\",\n" +
                "                \"floor\": \"2\",\n" +
                "                \"belongxiaqucode\": \"\",\n" +
                "                \"rowguid\": \"efc976f8-8a1b-4091-8046-91a0e8e9b713\",\n" +
                "                \"operatedate\": 1640656071000\n" +
                "            },\n" +
                "            {\n" +
                "                \"hallname\": \"一楼大厅\",\n" +
                "                \"centerguid\": \"ca38999e-dde6-4d63-a873-2115040e205a\",\n" +
                "                \"yearflag\": \"\",\n" +
                "                \"operateusername\": \"\",\n" +
                "                \"ordernum\": 0,\n" +
                "                \"id\": \"\",\n" +
                "                \"row_id\": \"\",\n" +
                "                \"floor\": \"1\",\n" +
                "                \"belongxiaqucode\": \"\",\n" +
                "                \"rowguid\": \"ecdaaf59-ee9a-482e-b99e-35625fe85bbd\",\n" +
                "                \"operatedate\": 1640656071000\n" +
                "            }\n" +
                "        ],\n" +
                "        \"code\": 1,\n" +
                "        \"text\": \"\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"text\": \"\"\n" +
                "    }\n" +
                "}");

        return config;
    }
    public AbstractConfig config2() {
        AbstractConfig config = new DocConfig();

        config.setNAME("部门列表");
        config.setMETHOD("POST");
        config.setDESCRIPTION("application/json;charset=utf-8;");
        config.setURL("http://localhost:8070/epoint-web-zwdt/rest/sfqhjqueue/getOUList");
        config.setCONTENT_TYPE("application/json;charset=utf-8;");
        config.setACCEPT("json;");
        config.setINPUT("{\n" +
                "\t\"token\" : \"\",\n" +
                "\t\"params\" : {\n" +
                "\t\t\"centerguid\" : \"ca38999e-dde6-4d63-a873-2115040e205a\", \n" +
                "\t\t\"hallguid\" : \"all\",\n" +
                "\t\t\"currentpage\" : 0,\n" +
                "\t\t\"pagesize\" : 2\n" +
                "\t}\n" +
                "}");
        config.setOUTPUT("{\n" +
                "    \"custom\": {\n" +
                "        \"totalcount\": \"11\",\n" +
                "        \"code\": 1,\n" +
                "        \"oulist\": [\n" +
                "            {\n" +
                "                \"ouguid\": \"a6db6eb4-a093-4688-a82e-9584baa107a1\",\n" +
                "                \"ougnum\": 0,\n" +
                "                \"ouname\": \"不动产中心\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ouguid\": \"36f2204c-e341-4e1f-9eeb-b73443e15709\",\n" +
                "                \"ougnum\": 0,\n" +
                "                \"ouname\": \"房地产交易中心\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"text\": \"\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"text\": \"\"\n" +
                "    }\n" +
                "}");

        return config;
    }



//    public JSONArray tmpData1() {
//        JSONArray dataRows = new JSONArray();
//
//        // 有多接口时,添加多组 JSONObject 数据
//        for (int i = 0; i < 1; i++) {
//            JSONObject data = new JSONObject();
//
//            data.put("interface_name", IConfig_1.NAME);
//            data.put("interface_url", IConfig_1.URL);
//            data.put("interface_reqdata", IConfig_1.INPUT());
//            data.put("interface_retdata", IConfig_1.OUTPUT());
//            // 单独的不通用的配置信息, 从单独的配置类中编写
//            data.put("interface_description", IConfig_1.DESCRIPTION);
//            dataRows.add(data);
//        }
//
//        return dataRows;
//    }

}
