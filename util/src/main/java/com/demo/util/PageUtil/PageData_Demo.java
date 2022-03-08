package com.demo.util.PageUtil;

import com.zhq.util.JsonUtil.JsonUtil;
import com.zhq.util.PageUtil.PageData;

import java.util.ArrayList;
import java.util.List;

/**
 * PageData 类的 Demo
 * @author Eddie Zhang
 */
public class PageData_Demo {
    public static void main(String[] args) {
        PageData_Demo instance = new PageData_Demo();

        instance.demo1();
    }

    /**
     * demo : hasNextPage, getPageSize, getTotalSize, getPageCount, getPageContent
     */
    public void demo1() {
        List<Integer> strLists = new ArrayList<Integer>();
        strLists.add(1);
        strLists.add(2);
        strLists.add(4);
        strLists.add(null);
        strLists.add(6);

        PageData pageData = new PageData(strLists, 2, 2);
        System.out.println(pageData.hasNextPage());

        System.out.println(pageData.getPageSize());

        System.out.println(pageData.getTotalSize());

        System.out.println(pageData.getPageCount());

        System.out.println(JsonUtil.listToJString(pageData.getPageContent()));
    }
}
