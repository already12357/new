package com.zhq.util;

import com.aspose.cells.Workbook;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import org.apache.tools.ant.types.selectors.TypeSelector;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.*;
import java.util.Base64;
import java.util.Locale;

public class IOUtil {
    public static final byte[] PNG_PREFIX = { 0x89, 0x50, 0x4e, 0x47 };

    public static final String PNG = "png";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String PPT = "ppt";
    public static final String PPTX = "pptx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";
    public static final String PDF = "pdf";

//    public static String getTypeByBytes() {
//
//    }

    /**
     * 用于解析对应的文件后缀名
     * @param file 解析的文件名
     * @return
     */
    public static String parseSuffix(File file) {
        String filePath = file.getAbsolutePath();
        String[] fileParts = filePath.split("\\.");
        String suffix = fileParts[fileParts.length - 1];

        return suffix.toLowerCase(Locale.ENGLISH);
    }

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
     * 使用 Aspose 将传出的文件转化为 pdf 文件并返回
     * @param file 需要转化的文件
     * @return
     */
    public static File toPdf(File file) {
        String suffix = parseSuffix(file);
        File tempPdfFile = null;
        FileOutputStream fout = null;

        try {
            if (null == tempPdfFile || !tempPdfFile.exists()) {
                // 通过临时文件来存储存储在线显示的内容
                tempPdfFile = File.createTempFile("temp", ".pdf");
            }

            fout = new FileOutputStream(tempPdfFile);

            switch (suffix) {
                case DOC:
                case DOCX:
                    Document document = new Document(file.getAbsolutePath());
                    document.save(fout, com.aspose.words.SaveFormat.PDF);
                    break;

                case PPT:
                case PPTX:
                    Presentation presentation = new Presentation(file.getAbsolutePath());
                    presentation.save(fout, com.aspose.slides.SaveFormat.Pdf);
                    break;

                case XLS:
                case XLSX:
                    Workbook workbook = new Workbook(file.getAbsolutePath());
                    workbook.save(fout, com.aspose.cells.SaveFormat.PDF);
                    break;

                case PDF:
                    tempPdfFile = file;
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fout);
        }

        return tempPdfFile;
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
     * 读取输入流中的字节数据
     * @param in 包含数据的输入流
     * @return
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
}
