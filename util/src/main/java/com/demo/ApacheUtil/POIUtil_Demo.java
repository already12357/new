package com.demo.ApacheUtil;

import com.zhq.util.ApacheUtil.POIUtil;
import com.zhq.util.AsposeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIUtil_Demo {
    public static void main(String[] args) {
        POIUtil_Demo instance = new POIUtil_Demo();

        instance.demo1();
    }

    public void demo1() {
        File docFile = new File("C:\\Users\\Administrator\\Desktop\\int_document_doc.doc");
        File destFile = new File("C:\\Users\\Administrator\\Desktop\\int_document_doc2.doc");

        Map<String, String> mergedMap = new HashMap<String, String>();
        mergedMap.put("<<1>>", "11111");
        mergedMap.put("<<2>>", "222222");

        AsposeUtil.replaceFieldsToDoc(docFile, destFile, mergedMap);

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
    }
}
