package com.demo.IOUtil;

import com.config.CommonConfig;
import com.zhq.util.IOUtil.*;
import com.zhq.util.IOUtil.IOUtil;
import com.zhq.util.ResourceUtil;

import java.io.File;
import java.io.FileInputStream;

/**
 * IOUtil 的 Demo
 * @author Eddie Zhang
 */
public class IOUtil_Demo {
    public static void main(String[] args) {
        IOUtil_Demo instance = new IOUtil_Demo();

        instance.demo1();
        instance.demo2();
        instance.demo3();
        instance.demo4();
    }

    /**
     * demo : fileSuffix
     */
    public void demo1() {
        File file = new File(CommonConfig.DEMO_FILE_LOCATION + "io/io_demo_1.docx");

        // 使用 fileSuffix 根据文件名，获取其文件后缀
        System.out.println(IOUtil.fileSuffix(file));
    }

    /**
     * demo : copyFile
     */
    public void demo2() {
        String sourcePath = CommonConfig.DEMO_FILE_LOCATION + "io/io_demo_2.txt";
        String destPath = CommonConfig.DEMO_FILE_LOCATION + "io/out/io_demo_2.1_out.txt";
        String destPath2 = CommonConfig.DEMO_FILE_LOCATION + "io/out/io_demo_2.2_out.txt";
        File source = new File(sourcePath);
        File destFile = new File(destPath);
        FileInputStream fin = null;

        try {
            fin = new FileInputStream(sourcePath);

            // 使用 copyFile 对文件进行复制
            IOUtil.copyFile(source, destFile);
            IOUtil.copyFile(fin, destPath2);
            IOUtil.copyFile(fin, CommonConfig.DEMO_FILE_LOCATION + "io/out", "io_demo_2.3_out.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }

    /**
     * demo : bytesPrint, bytesInFile, bytesInStream
     */
    public void demo3() {
        File imgFile = new File(CommonConfig.DEMO_FILE_LOCATION + "io/io_demo_3.png");
        IOUtil.bytesPrint(imgFile);
        System.out.println("\n--------------------------------\n");

        // 使用 bytesInFile, bytesInStream 读取文件或流中的字节数据
        byte[] imageFileBytes = IOUtil.bytesInFile(imgFile);
        IOUtil.bytesPrint(imageFileBytes);
        System.out.println("\n--------------------------------\n");

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(imgFile);
            // 使用 bytesInFile, bytesInStream 读取文件或流中的字节数据
            byte[] imageStreamBytes = IOUtil.bytesInStream(fin);
            IOUtil.bytesPrint(imageStreamBytes);
            System.out.println("\n--------------------------------\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }

    /**
     * demo : bytesInFile, getDataUrlByFormat, DATA_URL
     */
    public void demo4() {
        File imgFile = new File(CommonConfig.DEMO_FILE_LOCATION + "io/io_demo_4.png");
        byte[] imageFileBytes = IOUtil.bytesInFile(imgFile);

        // 使用 DataUrl 类生成对应的 dataUrl
        DataUrl pngDUrl = DataUrl.getDataUrlByFormat(IOConstant.PNG);
        System.out.println(pngDUrl.DATA_URL(imageFileBytes));
    }
}
