package com.zhq.util.IOUtil;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import com.zhq.util.ResourceUtil;

import java.io.*;
import java.util.*;

/**
 * IO 流，文件有关的帮助类
 * @author Eddie Zhang
 */
public class IOUtil {
    /**
     * 用于解析对应的文件后缀名
     * @param file 解析的文件名
     * @return
     */
    public static String fileSuffix(File file) {
        try {
            String filePath = file.getAbsolutePath();
            String[] fileParts = filePath.split("\\.");
            String suffix = fileParts[fileParts.length - 1];

            return suffix.toLowerCase(Locale.ENGLISH);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将一个文件从一个地点拷贝到另一个位置
     * @param fromFile,fromStream 文件的出发位置 ( 路径, 流  )
     * @param toFile,toPath 文件的拷贝目标位置  ( 路径, 流 )
     */
    public static void copyFile(File fromFile, File toFile) {
        FileInputStream fin = null;

        try {
            fin = new FileInputStream(fromFile);
            copyFile(fin, toFile.getAbsolutePath());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }
    public static void copyFile(InputStream fromStream, String toPath) {
        File targetDir = null;
        FileOutputStream fout = null;
        byte[] buffer = new byte[1024];

        try {
            targetDir = new File(toPath);

            if (!targetDir.exists()) {
                // 建立路径上没有的文件夹
                File parentFile = targetDir.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }

                targetDir.createNewFile();
            }

            fout = new FileOutputStream(targetDir);
            int readCount = 0;

            while ((readCount = fromStream.read(buffer)) != -1) {
                fout.write(buffer, 0, readCount);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fout);
        }
    }

    /**
     * 将一个文件从一个地点拷贝到特定的文件夹下
     * @param fromStream 文件流
     * @param toDir 文件夹路径
     * @param fileName 文件名称 ( 不包含后缀 )
     */
    public static void copyFile(InputStream fromStream, String toDir, String fileName) {
        String filePath = toDir.concat(File.separator).concat(fileName);
        copyFile(fromStream, filePath);
    }


//    /**
//     * 使用 Aspose 将传出的文件转化为 pdf 文件并返回一个
//     * @param file 需要转化的文件
//     * @return
//     */
//    public static File toPdf(File file) {
//        String suffix = fileSuffix(file);
//        File tempPdfFile = null;
//        FileOutputStream fout = null;
//
//        try {
//            if (null == tempPdfFile || !tempPdfFile.exists()) {
//                // 通过临时文件来存储存储在线显示的内容
//                tempPdfFile = File.createTempFile("temp", ".pdf");
//            }
//
//            fout = new FileOutputStream(tempPdfFile);
//
//            // 根据文件不同类型，调用不同 aspose 对象的 save 方法
//            switch (suffix) {
//                case IOConstant.DOC:
//                case IOConstant.DOCX:
//                    Document document = new Document(file.getAbsolutePath());
//                    document.save(fout, com.aspose.words.SaveFormat.PDF);
//                    break;
//
//                case IOConstant.PPT:
//                case IOConstant.PPTX:
//                    Presentation presentation = new Presentation(file.getAbsolutePath());
//                    presentation.save(fout, com.aspose.slides.SaveFormat.Pdf);
//                    break;
//
//                case IOConstant.XLS:
//                case IOConstant.XLSX:
//                    Workbook workbook = new Workbook(file.getAbsolutePath());
//                    workbook.save(fout, com.aspose.cells.SaveFormat.PDF);
//                    break;
//
//                case IOConstant.PDF:
//                    tempPdfFile = file;
//                    break;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            ResourceUtil.closeResources(fout);
//        }
//
//        return tempPdfFile;
//    }

//    /**
//     * 将对应的文件存储到对应的数据库中的对应字段
//     * @param from
//     * @param dataSource
//     */
//    public static void storeFile(File from, DataSource dataSource) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//    }

    /**
     * 将字节数据写入文件
     * @param file 文件对象
     * @param data 写入文件的二进制数据
     */
    public static void writeBytesToFile(File file, byte[] data) {
        FileOutputStream fout = null;

        try {
            fout = new FileOutputStream(file);
            fout.write(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fout);
        }
    }

    /**
     * 读取 输入流 ( InputStream ) 或 文件 ( File ) 中的字节数据
     * @return 返回其中的文件数据
     */
    public static byte[] bytesInStream(InputStream in) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] content = new byte[2048];

        try {
            int readLength = 0;
            while ((readLength = in.read(content)) != -1) {
                bao.write(content, 0, readLength);
            }

            return bao.toByteArray();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] bytesInFile(File file) {
        if (null == file) {
            return null;
        }

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            return bytesInStream(fin);
        }
        catch (Exception e) {
            return null;
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }

    /**
     * 输出传入的二进制字节
     * @param bytes 输出的二进制字节
     */
    public static void bytesPrint(byte[] bytes) {
        if (null == bytes || bytes.length == 0) { return; }

        for (int i = 0; i < bytes.length; i++) {
            if (i % 16 == 0) {
                System.out.println("");
            }

            System.out.print(bytes[i] + " ");
        }

        System.out.flush();
    }

    /**
     * 输出文件内容的二进制字节
     * @param file 传入的文件对象
     */
    public static void bytesPrint(File file) {
        byte[] fileBytes = IOUtil.bytesInFile(file);
        bytesPrint(fileBytes);
    }
}
