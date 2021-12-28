package com.demo.PageUtil;

import com.zhq.util.JsonUtil.JsonUtil;
import com.zhq.util.PageUtil.PageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * PageUtil 类的 Demo
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

        // 使用 pageList 对查询的结果进行分页
        // 使用 <T> 来对分页类型进行限定
        List<Integer> pagedStrList = PageUtil.<Integer>pageList(strLists, 1, 2);
        System.out.println(JsonUtil.listToJString(pagedStrList));
    }
}
