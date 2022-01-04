package com.zhq.util.ApacheUtil;

import com.zhq.util.IOUtil.IOUtil;
import com.zhq.util.ResourceUtil;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.model.FieldsDocumentPart;
import org.apache.poi.hwpf.usermodel.Field;
import org.apache.poi.hwpf.usermodel.Fields;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

/**
 * Apache 中操作 office 的 poi 帮助包
 */
public class POIUtil {
    /**
     * 替换 doc 文件中定义的 field 部分
     * @param docFile 基础的模板文档
     * @param destFile 替换后的输出文档
     * @param field 变量名称
     * @param value 替换编辑域中的值
     */
    public static void replaceFieldsInDoc(File docFile, File destFile, String field, String value) {
        FileInputStream fin = null;
        FileOutputStream fout = null;

        try {
            fin = new FileInputStream(docFile);
            HWPFDocument document = new HWPFDocument(fin);

            // 获取编辑域 ( merge fields ) 变量
            Fields fields = document.getFields();
            Iterator<Field> iterator = fields.getFields(FieldsDocumentPart.MAIN).iterator();

            while (iterator.hasNext()) {
                Field iterField = iterator.next();
                System.out.println(iterField.getType());
            }

            Range range = document.getRange();
            System.out.println(range.text());

            range.replaceText("${1}", "1111");
            range.replaceText("${2}", "2222");

            // 修改后写出
            IOUtil.copyFile(docFile, destFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }
}
