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
    // 各种图片的二进制流对应的前缀
    public static final byte[] BMP_BIN_PREFIX = {0x42, 0x4d};
    public static final byte[] GIF_BIN_PREFIX = {0x47, 0x49, 0x46, 0x38};
    public static final byte[] JPG_BIN_PREFIX = {(byte) 0xff, (byte) 0xd8, (byte) 0xff,(byte) 0xe0};
    public static final byte[] PNG_BIN_PREFIX = {(byte) 0x89, 0x50, 0x4e, 0x47 };

    // 各种文件后缀类型
    public static final String BMP = "bmp";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String GIF = "gif";
    public static final String JPG = "jpg";
    public static final String PDF = "pdf";
    public static final String PNG = "png";
    public static final String PPT = "ppt";
    public static final String PPTX = "pptx";
    public static final String XLS = "xls";
    public static final String XLSX = "xlsx";

    // 各种文件大类
    public static final String TYPE_IMG = "img";
    public static final String TYPE_TEXT = "text";

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

    /**
     * 根据文件流返回对应的类型
     * @param fileBytes 传入文件的二进制类型
     * @param fileType 文件大类
     * @return
     */
    public static String typeInBytes(byte[] fileBytes, String fileType) {

    }
}
