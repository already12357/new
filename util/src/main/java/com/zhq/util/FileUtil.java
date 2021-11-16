package com.zhq.util;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.aspose.pdf.operators.Do;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.activation.FileTypeMap;
import javax.sql.DataSource;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {
    /**
     * 将一个文件从一个地点拷贝到另一个文件
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
            ResourceUtil.closeResources(fin, fout);
        }
    }


    /**
     * 将传出的文件转化为 pdf 文件并返回
     * @param file 传入的文件
     * @param pdfPath 转化后的文件地址
     * @return
     */
    public static File toPdf(File file, String pdfPath) {
        File pdfFile = new File(pdfPath);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(pdfFile);
            // 建立对应的文件
            Document document = new Document(new FileInputStream(file));
             // 调用 save 方法转化
            document.save(fout, SaveFormat.Pdf);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fout);
        }
    }


    /**
     * 将对应的文件存储到对应的数据库中的对应字段
     * @param from
     * @param dataSource
     */
    public static void storeFile(File from, DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
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
