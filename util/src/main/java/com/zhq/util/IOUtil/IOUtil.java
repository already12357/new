package com.zhq.util.IOUtil;

import com.aspose.cells.Workbook;
import com.aspose.psd.internal.bD.E;
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
    // 各种文件格式的二进制流对应的前缀
    public static final byte[] BMP_BIN_PREFIX = {0x42, 0x4d};
    public static final byte[] GIF_BIN_PREFIX = {0x47, 0x49, 0x46, 0x38};
    public static final byte[] JPG_BIN_PREFIX = {(byte) 0xff, (byte) 0xd8, (byte) 0xff};
    public static final byte[] PNG_BIN_PREFIX = {(byte) 0x89, 0x50, 0x4e, 0x47 };

    // 各种文件格式类型
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
    public static final String ZIP = "zip";
    public static final String RAR = "rar";

    // 各种文件大类
    // 图片
    public static final String TYPE_IMAGE = "image";
    // 文本
    public static final String TYPE_TEXT = "text";

    // 文件头魔数映射
    public static Map<String, byte[]> MAGIC_MAPS = new HashMap<String, byte[]>() {
        {
            put(BMP, BMP_BIN_PREFIX);
            put(GIF, GIF_BIN_PREFIX);
            put(PNG, PNG_BIN_PREFIX);
            put(JPG, JPG_BIN_PREFIX);
        }
    };



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
        String filePath = toDir.concat("/").concat(fileName);
        copyFile(fromStream, filePath);
    }


    /**
     * 使用 Aspose 将传出的文件转化为 pdf 文件并返回
     * @param file 需要转化的文件
     * @return
     */
    public static File toPdf(File file) {
        String suffix = fileSuffix(file);
        File tempPdfFile = null;
        FileOutputStream fout = null;

        try {
            if (null == tempPdfFile || !tempPdfFile.exists()) {
                // 通过临时文件来存储存储在线显示的内容
                tempPdfFile = File.createTempFile("temp", ".pdf");
            }

            fout = new FileOutputStream(tempPdfFile);

            // 根据文件不同类型，调用不同 aspose 对象的 save 方法
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

//    /**
//     * 将对应的文件存储到对应的数据库中的对应字段
//     * @param from
//     * @param dataSource
//     */
//    public static void storeFile(File from, DataSource dataSource) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//    }


    /**
     * 根据魔数获取文件的格式
     * @param magicBytes 传入的魔数数据
     * @return
     */
    private static String fileFormatInMagicBytes(byte[] magicBytes) {
        if (null == magicBytes) {
            return null;
        }

        for (Map.Entry<String, byte[]> magicMap : MAGIC_MAPS.entrySet()) {
            boolean matched = true;
            byte[] iBytes = magicMap.getValue();

            for (int i = 0; i < iBytes.length; i++) {
                if (iBytes[i] != magicBytes[i]) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                return magicMap.getKey();
            }
        }

        return null;
    }

    /**
     * 根据 文件流 或 文件 返回不同大类的文件类型 ( 图片, 文本 )
     * @param imageBytes
     * @return
     */
    public static String imgTypeInBytes(byte[] imageBytes) {
        return typeInBytes(imageBytes, TYPE_IMAGE);
    }

    public static String imgTypeInFile(File file) {
        return typeInFile(file, TYPE_IMAGE);
    }




    public static String textTypeInBytes(byte[] textBytes) {
        return typeInBytes(textBytes, TYPE_TEXT);
    }

    public static String textTypeInFile(File file) {
        return typeInFile(file, TYPE_TEXT);
    }



    /**
     * 根据 文件流 或 文件 返回对应的类型
     * @param fileBytes 传入文件的二进制类型
     * @param fileType 文件大类
     * @return
     */
    public static String typeInBytes(byte[] fileBytes, String fileType) {
        // 获取文件的魔数
        byte[] magicBytes = magicBytesInBytes(fileBytes);
        // 根据魔数获取文件格式
        String fileFormat = fileFormatInMagicBytes(magicBytes);
        return fileType.concat("/").concat(fileFormat);
    }

    public static String typeInFile(File file, String fileType) {
        byte[] fileBytes = bytesInFile(file);
        return typeInBytes(fileBytes, fileType);
    }



    /**
     * 读取 输入流 或 文件 中的字节数据
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
     * 根据文件中的字节数 或 文件对象 匹配文件头中的魔数
     * @param fileBytes 文件内容
     * @return
     */
    public static byte[] magicBytesInBytes(byte[] fileBytes) {
        if (null == fileBytes) {
            return null;
        }

        Collection<byte[]> magicBytes = MAGIC_MAPS.values();

        // 遍历所有的文件头魔数, 对比返回对应的文件头魔数
        for (byte[] iBytes : magicBytes) {
            boolean matched = true;

            for (int i = 0; i < iBytes.length; i++) {
                Byte iByte = new Byte(iBytes[i]);
                Byte fByte = new Byte(fileBytes[i]);

                if (iByte.compareTo(fByte) != 0) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                return iBytes;
            }
        }

        return null;
    }

    public static byte[] magicBytesInFile(File file) {
        try {
            byte[] fileBytes = bytesInFile(file);
            return magicBytesInBytes(fileBytes);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    /**
     * 根据对应是数据流 或 文件对象，生成文件的 DataUrl
     *
     * 1. 从 文件 或 流 中读取对应的字节内容
     * 2. 根据字节内容的前几个字节，匹配对应的魔数字节
     * 3. 根据匹配的魔数字节，生成 dataUrl 所需的文件类型，base64 编码等
     * 4. 拼接生成最后结果
     *
     * @param imgIn 输入的图片流
     * @param base64 是否使用 base64 编码 ( 默认使用, true )
     * @return
     */
    public static String imgDataUrl(InputStream imgIn, boolean base64) {
        try {
            // 返回的流对象
            StringBuilder imgUrl = new StringBuilder("");
            // 具体的二进制流
            byte[] imageBytes = bytesInStream(imgIn);
            // 对应的图片
            String dataType = imgTypeInBytes(imageBytes);


            // 拼接对应的 data url 内容
            imgUrl.append("data: ");
            imgUrl.append(dataType);
            if (base64) {
                String base64ImgStr = Base64.getEncoder().encodeToString(imageBytes);
                imgUrl.append(";base64,");
                imgUrl.append(base64ImgStr);
            }
            else {
                String imgStr = new String(imageBytes);
                imgUrl.append(",");
                imgUrl.append(imgStr);
            }

            return imgUrl.toString();
        }
        catch (Exception e) {
            return null;
        }
    }
    public static String imgDataUrl(File file, boolean base64) {
        FileInputStream fin = null;

        try {
            fin = new FileInputStream(file);
            return imgDataUrl(fin, base64);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        finally {
            ResourceUtil.closeResources(fin);
        }
    }

    public static String imgDataUrl(InputStream imgIn) {
        return imgDataUrl(imgIn, true);
    }
    public static String imgDataUrl(File file) {
        return imgDataUrl(file, true);
    }


    /**
     * 输出文件内容的二进制字节
     * @param file 传入的文件对象
     */
    public static void printBinaryContent(File file) {
        byte[] fileBytes = IOUtil.bytesInFile(file);

        for (int i = 0; i < fileBytes.length; i++) {
            System.out.print(fileBytes[i] + " ");
        }

        System.out.flush();
    }
}
