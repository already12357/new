package com.zhq;


import com.config.CommonConfig;
import com.zhq.util.IOUtil.DataUrl;
import com.zhq.util.IOUtil.IOConstant;
import com.zhq.util.IOUtil.IOUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        // 文件内容
        String fileName = "";
        FileInputStream imgStream = new FileInputStream(fileName);
        String srcImg = DataUrl.DATA_URL(IOConstant.PNG, IOUtil.bytesInStream(imgStream));
        System.out.println(srcImg);
    }
}
