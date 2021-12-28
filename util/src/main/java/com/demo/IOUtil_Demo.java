package com.demo;

import com.zhq.util.IOUtil;

import java.io.File;

/**
 * IOUtil 的 Demo
 */
public class IOUtil_Demo {
    public static void main(String[] args) {
        IOUtil_Demo instance = new IOUtil_Demo();

//        instance.demo1();
        instance.demo2();
    }

    public void demo1() {
        File file = new File("./src/main/java/com/demo/IOUtil_Demo.java");

        // 使用 fileSuffix 根据文件名，获取其文件后缀
        System.out.println(IOUtil.fileSuffix(file));
    }

    public void demo2() {
        File source = new File("./src/main/java/com/demo/IOUtil_Demo.java");
        File destFile = new File("./src/main/resources/file_template/destfile.java");
        IOUtil.copyFile(source, destFile);
    }
}
