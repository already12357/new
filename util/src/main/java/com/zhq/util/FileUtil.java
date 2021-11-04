package com.zhq.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.activation.FileTypeMap;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {
//    private static Map<String, String> fileType = new HashMap<>();
//    static {
//        // 存入不同文件的前四个用于表示文件类型的字节
//        // 用于统计对应的文件类型
//        fileType.put("FFD8FF",".jpg");
//        fileType.put("FFD8FFE0", ".jpg");
//        fileType.put("FFD8FFE1", ".jpg");
//        fileType.put("89504E47", ".png");
//        fileType.put("47494638", ".gif");
//        fileType.put("49492A00", ".tif");
//        fileType.put("3C3F786D6C",".xml");
//    }
//
//    private static String fileType(File file) {
//        byte[] typeBytes = new byte[4];
//        FileInputStream fin = null;
//        String fileType = null;
//
//        try {
//            fin = new FileInputStream(file);
//            int readCount = fin.read(typeBytes, 0, typeBytes.length);
//
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return fileType;
//    }

    /**
     * 将一个文件从一个地点拷贝到另一个地点
     * @param from 文件的出发地
     * @param to 文件的目的地
     */
    public static void copyFile(File from, File to) {
        FileInputStream fin = null;
        FileOutputStream fout = null;
        byte[] buffer = new byte[1024];

        try {
            fin = new FileInputStream(from);
            fout = new FileOutputStream(to);

            while (fin.read(buffer) != -1) {
                fout.write(buffer);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (fout != null) {
                    fout.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 读取 Excel 文件 ( .xlsx 结尾 )
     * @param file 表格文件
     */
    public static List<Cell> readSpreadSheet(File file) {
        List<Cell> sheetCells = new ArrayList<>();

        try {
            Workbook exclWorkBook = WorkbookFactory.create(file);

            // 工作簿 ==> 工作表 ==> 行 ==> 列 ( 格子 )
            for (Sheet sheet : exclWorkBook) {
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        sheetCells.add(cell);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return sheetCells;
    }
}
