package com.demo;

import com.zhq.util.AsposeUtil;
import com.zhq.util.IOUtil.IOUtil;

import javax.print.attribute.standard.Fidelity;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ApacheUtil 类的 Demo
 * @author Eddie Zhang
 */
public class AsposeUtil_Demo {
    public static void main(String[] args) {
        AsposeUtil_Demo instance = new AsposeUtil_Demo();

//        instance.demo1();
        instance.demo2();
    }

    public void demo1() {

    }

    public void demo2() {
        File docFile = new File("C:\\Users\\Administrator\\Desktop\\int_document_doc.doc");
        File destFile = new File("C:\\Users\\Administrator\\Desktop\\int_document_doc2.doc");

        Map<String, Object> mergedMap = new HashMap<String, Object>();
        mergedMap.put("1", "11111");
        mergedMap.put("2", "222222");

        AsposeUtil.replaceFieldsToDoc(docFile, destFile, mergedMap);
    }
}
