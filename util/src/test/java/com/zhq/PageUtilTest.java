package com.zhq;

import com.zhq.util.pageUtil.PageUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PageUtilTest {
    @Test
    public void testPageUtil() {
        List<String> listContent = new ArrayList<>();

        listContent.add("String1");
        listContent.add("String2");
        listContent.add("String3");
        listContent.add("String4");
        listContent.add("String5");
        listContent.add("String6");
        listContent.add("String7");
        listContent.add("String8");

        listContent.add("String5");
        listContent.add("String6");
        listContent.add("String7");
        listContent.add("String8");


        System.out.println(PageUtil.beginIndex(listContent, 1, 10));
        System.out.println(PageUtil.endIndex(listContent, 1, 10));
        List<String> pageList = PageUtil.pageList(listContent, 0, 10);
        pageList.forEach(System.out::println);
    }
}
