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
    }

    public void demo1() {
        List<Integer> strLists = new ArrayList<Integer>();
        strLists.add(1);
        strLists.add(2);
        strLists.add(4);
        strLists.add(null);
        strLists.add(6);

        Set<Object> objectsSet = new HashSet<>();
        objectsSet.add("2");
        objectsSet.add(1);
        objectsSet.add("4");
        objectsSet.add(new Date());
        objectsSet.add("3");
        objectsSet.add(2);
        objectsSet.add(null);

        Object[] pagingArray = new Object[10];
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                pagingArray[i] = i;
            }
            else {
                pagingArray[i] = "2" + i;
            }
        }

        // 使用 pageList, pageSet 对集合对象进行分页
        List<Integer> pagedStrList = PageUtil.pageList(strLists, 1, 2);
        System.out.println(JsonUtil.listToJString(pagedStrList));

        Set<Object> pagedObjSet = PageUtil.pageSet(objectsSet, 1, 3);
        System.out.println(JsonUtil.setToJString(pagedObjSet));

        Object[] pagedArray = PageUtil.pageArray(pagingArray, 2, 2);
        System.out.println(JsonUtil.arrayToJString(pagedArray));
    }
}
