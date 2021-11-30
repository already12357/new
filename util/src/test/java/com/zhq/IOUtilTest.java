package com.zhq;

import com.zhq.util.IOUtil;
import com.zhq.util.ResourceUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class IOUtilTest {
    @Test
    public void testIOUtil() {
        InputStream fin = null;
        byte[] bytes = null;

        try {
            fin = new FileInputStream(new File("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/config/quartz/quartz.properties"));
            bytes = IOUtil.bytesInStream(fin);
            for (int i = 0; i < bytes.length; i++) {
                char bytesChar = (char) bytes[i];
                System.out.print(bytesChar);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }

    }
}
