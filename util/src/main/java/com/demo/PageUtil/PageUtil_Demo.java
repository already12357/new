package com.demo.PageUtil;

import com.zhq.util.JsonUtil.JsonUtil;
import com.zhq.util.PageUtil.PageUtil;

import java.util.*;

/**
 * PageUtil 类的 Demo
 * @author Eddie Zhang
 */
public class PageUtil_Demo {
    public static void main(String[] args) {
        PageUtil_Demo instance = new PageUtil_Demo();

        instance.demo1();
        instance.demo2();
    }

    /**
     * demo : pageListFromPage, pageArrayFromPage,
     */
    public void demo1() {
        List<Integer> strLists = new ArrayList<Integer>();
        strLists.add(1);
        strLists.add(2);
        strLists.add(4);
        strLists.add(null);
        strLists.add(6);

        Object[] pagingArray = new Object[10];
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                pagingArray[i] = i;
            }
            else {
                pagingArray[i] = "2" + i;
            }
        }

        // 使用 pageList, pageSet 对集合对象进行分页, 根据页号分页
        List<Integer> pagedStrList = PageUtil.pageListFromPage(strLists, 1, 2);
        System.out.println(JsonUtil.listToJString(pagedStrList));

        Object[] pagedArray = PageUtil.pageArrayFromPage(pagingArray, 2, 2);
        System.out.println(JsonUtil.arrayToJString(pagedArray));
    }

    /**
     * demo : pageListFromItem
     */
    public void demo2() {
        List<Integer> strLists = new ArrayList<Integer>();
        strLists.add(1);
        strLists.add(2);
        strLists.add(4);
        strLists.add(null);
        strLists.add(6);

        List<Integer> pagedStrList = PageUtil.pageListFromItem(strLists, 6, 2);
        System.out.println(JsonUtil.listToJString(pagedStrList));
    }
}
