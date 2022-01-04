package com.demo;

import com.zhq.util.AsposeUtil;
import com.zhq.util.IOUtil.IOUtil;

import javax.print.attribute.standard.Fidelity;
import java.io.File;

/**
 * ApacheUtil 类的 Demo
 * @author Eddie Zhang
 */
public class AsposeUtil_Demo {
    public static void main(String[] args) {
        AsposeUtil_Demo instance = new AsposeUtil_Demo();

        instance.demo1();
    }

    public void demo1() {
        File destFile = new File("C:\\Users\\Administrator\\Desktop\\temp_doc_tepmlate.pdf");
        IOUtil.copyFile(AsposeUtil.toPdf(new File("C:\\Users\\Administrator\\Desktop\\int_document_doc.doc")), destFile);
    }
}
