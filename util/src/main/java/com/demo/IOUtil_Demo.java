package com.demo;

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

//        instance.demo1();
//        instance.demo2();
//        instance.demo3();
        instance.demo4();
//        instance.demo5();
    }

    public void demo1() {
        File file = new File("./src/main/java/com/demo/IOUtil_Demo.java");

        // 使用 fileSuffix 根据文件名，获取其文件后缀
        System.out.println(IOUtil.fileSuffix(file));
    }

    public void demo2() {
        String sourcePath = "./src/main/java/com/demo/IOUtil_Demo.java";
        String destPath = "./src/main/resources/file_template/destfile.java";
        File source = new File(sourcePath);
        File destFile = new File(destPath);
        FileInputStream fin = null;

        try {
            fin = new FileInputStream(sourcePath);

            // 使用 copyFile 对文件进行复制
            IOUtil.copyFile(source, destFile);
            IOUtil.copyFile(fin, destPath);
            IOUtil.copyFile(fin, "./src/main/resources/file_template", "destfile.java");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }

    public void demo3() {
        File imgFile = new File("./src/main/resources/file/bg.jpg");

        // 使用 bytesInFile, bytesInStream 读取文件或流中的字节数据
        byte[] imageFileBytes = IOUtil.bytesInFile(imgFile);
        System.out.println(imageFileBytes);



        FileInputStream fin = null;
        try {
            fin = new FileInputStream(imgFile);

            // 使用 bytesInFile, bytesInStream 读取文件或流中的字节数据
            byte[] imageStreamBytes = IOUtil.bytesInStream(fin);
            System.out.println(imageStreamBytes);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }

    public void demo4() {
        File imgFile = new File("./src/main/resources/file/Picture2.png");
        byte[] imgBytes = IOUtil.bytesInFile(imgFile);
        byte[] magicBytesInImg =  IOUtil.magicBytesInBytes(imgBytes);

        // 使用 magicBytesInFile 读取文件字节中的魔数部分
        System.out.println(magicBytesInImg);
    }

    public void demo5() {
        File imgFile = new File("./src/main/resources/file/bg.jpg");
        byte[] imageFileBytes = IOUtil.bytesInFile(imgFile);

        // 使用 typeInBytes, imgTypeInBytes, textTypeInBytes 根据文件字节生成对应的文件类型
        // 形如 image/jpg 可用于 dataurl 的拼接
//        String imageTypeString = IOUtil.imgTypeInBytes(imageFileBytes);
        String imageTypeString = IOUtil.typeInBytes(imageFileBytes, IOUtil.TYPE_IMAGE);
        System.out.println(imageTypeString);
    }
}
