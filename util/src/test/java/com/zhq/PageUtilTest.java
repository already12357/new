package com.zhq;

import com.zhq.util.PageUtil.PageUtil;
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


        System.out.println(PageUtil.beginIndex(listContent.size(), 1, 10));
        System.out.println(PageUtil.endIndexByPageIndex(listContent.size(), 2, 4));
        List<String> pageList = PageUtil.pageListFromPage(listContent, 2, 3);
        pageList.forEach(System.out::println);
    }
}
