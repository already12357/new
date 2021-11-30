package com.zhq;


import com.zhq.util.HttpUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class HttpUtilTest {
//    @Test
//    public void testHttpUtilTest() {
//        Map<String, String> xFormURLEncoded = new HashMap<>();
//        xFormURLEncoded.put("data", "logvWU/uOmyWWTta8ceByg6yit3D3olmQMUMJB406kbCYMgo6kYffFHubv7GaG8eX9h7LdlNjw7nQ3EfWjv1fn4GriwAL1H67boyXjXiMYM9zwNkDwy74U6WamhWLrnE");
//        String returnValue = HttpUtil.sendPost_xFormUrlEncoded("http://111.16.49.5:8082/archivesearch/pub/entarch/getEntByUniscid", xFormURLEncoded);
//        System.out.println(returnValue);
//
//        HttpUtil.receiveByteFromUrl();
//    }

    @Test
    public void testFileSuffix() throws FileNotFoundException {
        byte[] imgBytes =  HttpUtil.htmlImgSrc(new FileInputStream(new File("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/table_split_vertical.png")));
        byte[] imgTypeBytes = new byte[10];

//        for (int i = 0; i <; i++) {
//            System.out.printf("%02x  ", imgBytes[i]);
//        }

        System.out.println("\n");




        byte[] imgBytes2 =  HttpUtil.htmlImgSrc(new FileInputStream(new File("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/Picture3.png")));
        byte[] imgTypeBytes2 = new byte[10];
//        for (int i = 0; i <30; i++) {
//            System.out.printf("%c  ", imgBytes2[i]);
//        }
    }
}
