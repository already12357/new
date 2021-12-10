package com.zhq;

import com.zhq.util.StringUtil;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtilTest {
    @Test
    public void stringTest() {
//        System.out.println(StringUtil.paramFromURLStringByName("http://localhost:8090/epoint-web-zwfwznsbytj?guid=12&peoplerealifafa&fdsflj", "guid"));

        List<String> nameList = new ArrayList<>();
        nameList.add("zhq123");
        nameList.add("434");
        nameList.add("42");
        nameList.add("42");
        nameList.add(null);
//        nameList.add("Lisi");
//        nameList.add("");
//        nameList.add(null);
//        nameList.add("zhaoliu");
        System.out.println(StringUtil.mergeList(nameList, "[,]"));

        Map<String, String> contentType = new HashMap<>();
//        contentType.put("param1", null);
//        contentType.put("param2", "");
//        contentType.put("param3", "123");
        System.out.println(StringUtil.mergeMapToUrlParams(contentType));

        String text = "Hellow, this is a string by byte presentation";
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        System.out.println("textBytes" + textBytes);
        System.out.println(new String(textBytes));

    }
}
