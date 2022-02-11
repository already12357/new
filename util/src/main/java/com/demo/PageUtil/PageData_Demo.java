package com.demo.PageUtil;

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


    public void demo1() {
        List<Integer> strLists = new ArrayList<Integer>();
        strLists.add(1);
        strLists.add(2);
        strLists.add(4);
        strLists.add(null);
        strLists.add(6);

        PageData pageData = new PageData(strLists, 2, 1);
        System.out.println(pageData.hasNextPage());
    }
}
