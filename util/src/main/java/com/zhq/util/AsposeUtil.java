package com.zhq.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import com.aspose.words.MailMerge;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataSet;
import com.aspose.words.net.System.Data.DataTable;
import com.template._1.util.TemplateMailMergeDataSource;
import com.zhq.util.IOUtil.IOConstant;
import com.zhq.util.IOUtil.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * office 文档操作有关的 Aspose 类
 */
public class AsposeUtil {
    /**
     * 使用 Aspose 将传入的 word, xlsx 等文件转化为 pdf 文件并返回一个临时的 pdf 文件
     * @param file 需要转化的文件
     * @return 返回转化完成的临时文件名称
     */
    public static File toPdf(File file) {
        String suffix = IOUtil.fileSuffix(file);
        File tempPdfFile;
        FileOutputStream fout = null;

        try {
            // 通过临时文件来存储存储在线显示的内容
            tempPdfFile = File.createTempFile("temp", ".pdf");
            fout = new FileOutputStream(tempPdfFile);

            // 根据文件不同类型，调用不同 aspose 对象的 save 方法
            switch (suffix) {
                case IOConstant.DOC:
                case IOConstant.DOCX:
                    Document document = new Document(file.getAbsolutePath());
                    document.save(fout, com.aspose.words.SaveFormat.PDF);
                    break;

                case IOConstant.PPT:
                case IOConstant.PPTX:
                    Presentation presentation = new Presentation(file.getAbsolutePath());
                    presentation.save(fout, com.aspose.slides.SaveFormat.Pdf);
                    break;

                case IOConstant.XLS:
                case IOConstant.XLSX:
                    Workbook workbook = new Workbook(file.getAbsolutePath());
                    workbook.save(fout, com.aspose.cells.SaveFormat.PDF);
                    break;

                case IOConstant.PDF:
                    tempPdfFile = file;
                    break;
            }

            return tempPdfFile;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            ResourceUtil.closeResources(fout);
        }
    }


//    /**
//     * 替换 word 文档中的编辑域, 并将其复制到一个新的文档中
//     * @param template 模板文档
//     * @param destFile 替换后文件的位置
//     * @param replaceValues 替换的域值，支持多个前缀，使用 Map 来存储每个域值
//     * @param tableName 域名称
//     */
//    public static void replaceRegionFieldsToDoc(File template, File destFile, List<Map<String, Object>> replaceValues, String tableName) {
//        try {
//            Document templateDoc = new Document(template.getAbsolutePath());
//            String suffix = IOUtil.fileSuffix(template);
//
//            switch (suffix) {
//                case IOConstant.DOC:
//                case IOConstant.DOCX:
//                    /**
//                     * 使用自定义的数据源 TemplateMailMergeDataSource
//                     */
//                    templateDoc.getMailMerge().executeWithRegions(new TemplateMailMergeDataSource(replaceValues, tableName));
//                    templateDoc.save(destFile.getAbsolutePath(), com.aspose.words.SaveFormat.DOC);
//                    break;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 替换 word 文档中的编辑域, 用于没有区域前缀的情况
//     * @param template 需要替换域的文件
//     * @param destFile 替换后文件的位置
//     * @param replaceValues 替换的域值 ( 文本使用 String, 图片使用 byte[]  ), 使用 Map 类型传入
//     */
//    public static void replaceFieldsToDoc(File template, File destFile, Map<String, Object> replaceValues) {
//        try {
//            Document templateDoc = new Document(template.getAbsolutePath());
//            String suffix = IOUtil.fileSuffix(template);
//
//            switch (suffix) {
//                case IOConstant.DOC:
//                case IOConstant.DOCX:
//                    templateDoc.getMailMerge().execute(
//                            replaceValues.keySet().toArray(new String[0]),
//                            replaceValues.values().toArray(new Object[0])
//                            );
//                    templateDoc.save(destFile.getAbsolutePath(), com.aspose.words.SaveFormat.DOC);
//                    break;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




    /**
     * 替换 Aspose Word 中的编辑域内容 ( 注意 : 不包含表格, 仅对表格之外的编辑域替换内容 )
     * @param doc Aspose 文档对象
     * @param data 渲染编辑域所需的数据, 如下，以键值对形式出现
     *             {
     *                 "col1_name1":"col1_val1",
     *                 "col1_name2": col1_val2,
     *                 "col1_name3":"col1_val3"
     *             }
     */
    public static void fillWordFields(Document doc, JSONObject data) {
        try {
            if (null != data && data.size() > 0) {
                MailMerge mailMarge = doc.getMailMerge();
                String[] fields = new String[data.size()];
                Object[] values = new Object[data.size()];
                int index = 0;

                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    fields[index] = entry.getKey();
                    values[index++] = entry.getValue();
                }

                mailMarge.execute(fields, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 替换 Aspose Word 编辑域表格中的内容 ( 注意 : 有规律的表格数据 ) ，如下：
     * 《TableStart:{tableName}》
     * 《columns1》 | 《columns2》 | 《columns3》 | 《columns4》
     * --------------------------------------------------
     * 《fields1》  | 《fields2》  | 《fields3》  |《fields4》
     * 《/TableStart:{tableName}》
     *
     * @param doc 传入的 Aspose 文档对象
     * @param tableName 编辑域表格的名称 
     * @param colList 编辑域表格的列名集合
     * @param data 渲染编辑域表格所用的数据, 其中每一列的数据使用一个 JSONObject 存储, 所有数据组合为 JSONArray
     *             [
     *                 {
     *                     "col1_name1":"col1_val1",
     *                     "col1_name2": col1_val2,
     *                     "col1_name3":"col1_val3"
     *                 },
     *                 {
     *                     "col2_name1":"col2_val1",
     *                     "col2_name2": col2_val2,
     *                     "col2_name3":"col2_val3"
     *                 },
     *             ......
     *             ]
     */
    public static void fillWordTable(Document doc, String tableName, List<String> colList, JSONArray data) {
        try {
            DataTable dataTable = new DataTable(tableName);
            DataSet dataSet = new DataSet();

            // 添加需要渲染的表格列
            for (String columnName : colList) {
                dataTable.getColumns().add(columnName);
            }

            // 渲染表格数据
            for (int i = 0; i < data.size(); i++) {
                DataRow rowObject = dataTable.newRow();
                JSONObject rowData = data.getJSONObject(i);

                // 每列数据渲染
                for (int j = 0; j < colList.size(); j++) {
                    rowObject.set(j, rowData.get(colList.get(j)));
                }

                dataTable.getRows().add(rowObject);
            }

            dataSet.getTables().add(dataTable);
            doc.getMailMerge().executeWithRegions(dataSet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
