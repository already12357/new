package com.zhq;

import com.zhq.util.IOUtil.IOUtil;
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
            fin = new FileInputStream(new File("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/Picture2.png"));
            bytes = IOUtil.bytesInStream(fin);
            for (int i = 0; i < bytes.length; i++) {
//                char bytesChar = (char) bytes[i];
                System.out.printf("%x ", bytes[i]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }

    }


    //    /**
//     * 读取 Excel 文件 ( .xlsx 结尾 )
//     * @param file 表格文件
//     */
//    public static List<Cell> readSpreadSheet(File file) {
//        List<Cell> sheetCells = new ArrayList<>();
//
//        try {
//            Workbook exclWorkBook = WorkbookFactory.create(file);
//
//            // 工作簿 ==> 工作表 ==> 行 ==> 列 ( 格子 )
//            for (Sheet sheet : exclWorkBook) {
//                for (Row row : sheet) {
//                    for (Cell cell : row) {
//                        sheetCells.add(cell);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//
//        return sheetCells;
//    }

    @Test
    public void printBytesTest() {
        File file = new File("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file/Picture2.png");
        IOUtil.bytesPrint(file);
    }
}
