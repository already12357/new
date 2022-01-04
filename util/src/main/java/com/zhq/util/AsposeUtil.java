package com.zhq.util;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.net.System.Data.DataSet;
import com.zhq.util.IOUtil.IOConstant;
import com.zhq.util.IOUtil.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * office 文档操作有关的 Aspose 类
 */
public class AsposeUtil {
    /**
     * 使用 Aspose 将传入的 word, xlsx 等文件转化为 pdf 文件并返回
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


    /**
     * 替换 word 文档中的编辑域, 并将其复制到一个新的文档中
     * @param template 模板文档
     * @param destFile 替换后文件的位置
     * @param replaceValues 文本
     */
    public static void replaceFieldsToDoc(File template, File destFile, Map<String, String> replaceValues) {
        try {
            Document templateDoc = new Document(template.getAbsolutePath());
            String suffix = IOUtil.fileSuffix(template);

            switch (suffix) {
                case IOConstant.DOC:
                case IOConstant.DOCX:
                    templateDoc.getMailMerge().execute(
                            replaceValues.keySet().toArray(new String[0]),
                            replaceValues.values().toArray(new String[0])
                            );
                    templateDoc.save(destFile.getAbsolutePath(), com.aspose.words.SaveFormat.DOC);
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
