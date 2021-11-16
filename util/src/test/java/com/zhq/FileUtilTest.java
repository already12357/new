package com.zhq;

import com.zhq.util.FileUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.util.List;


public class FileUtilTest {
//    @Test
//    public void fileUtilTestMethod() {
//        File file = new File("C:\\Users\\Administrator\\Desktop\\util\\src\\main\\resources\\exclfile.xlsx");
//        List<Cell> cells = FileUtil.readSpreadSheet(file);
//
//        for (Object cell : cells) {
//            System.out.println(cell);
//        }
//
//        File toFile = new File("C:\\Users\\Administrator\\Desktop\\exclfile.xlsx");
//        FileUtil.copyFile(file, toFile);
//    }

    @Test
    public void fileUtilTestMethod2() {
        String pdfFilePath = "C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/file_test.pdf";
        String docxFilePath = "C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/file_test1.xlsx";
        File docxFile = new File(docxFilePath);
        FileUtil.toPdf(docxFile);
    }
}
