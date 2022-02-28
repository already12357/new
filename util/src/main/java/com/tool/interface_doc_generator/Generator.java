package com.tool.interface_doc_generator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.config.CommonConfig;
import com.tool.interface_doc_generator.config.IConfig_1;
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
     */
    public void generateInterface_Doc(JSONArray renderData) {
        try {
            // 设置文件模板路径
            File tmpDoc = new File(TMP_LOCATION);
            Document doc = new Document(tmpDoc.getAbsolutePath());
            AsposeUtil.fillWordTable(doc, "interface", mergeFieldList(), renderData);
            doc.save(CREATE_LOCATION);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成器运行入口, 运行该主函数来实现接口文件模板的渲染
     */
    public static void main(String[] args) {
        Generator instance = new Generator();

        // 渲染 1, 2, 3....
        instance.generateInterface_Doc(instance.tmpData1());
        // instance.generateInterface_Doc(instance.tmpData2());
        // instance.generateInterface_Doc(instance.tmpData3());
        // instance.generateInterface_Doc(instance.tmpData4());
        // .....
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
        colList.add("interface_pic");

        return colList;
    }


    /**
     * 渲染文档编辑域的数据
     * @return
     */
    public JSONArray tmpData1() {
        JSONArray dataRows = new JSONArray();

        // 有多接口时,添加多组 JSONObject 数据
        for (int i = 0; i < 2; i++) {
            JSONObject data = new JSONObject();

            data.put("interface_name", IDocConfig.NAME);
            data.put("interface_url", IDocConfig.URL);
            data.put("interface_reqdata", IDocConfig.INPUT());
            data.put("interface_retdata", IDocConfig.OUTPUT());
            // 单独的不通用的配置信息, 从单独的配置类中编写
            data.put("interface_pic", IConfig_1.PIC);
            data.put("interface_description", IConfig_1.DESCRIPTION);
            dataRows.add(data);
        }

        return dataRows;
    }
}
