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
        FileInputStream imgStream = new FileInputStream("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/table_split_vertical.png");
        String srcImg = HttpUtil.imgDataUrl(imgStream, true);
        System.out.println(srcImg);
    }
}
