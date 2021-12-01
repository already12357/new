package com.zhq.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    // 默认的文件上传路径
    public static final String DEFAULT_LOADPATH = "C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/file";

    // 文件上传路径
    public static String loadPath = "";
    public static String getLoadPath() {
        if (!StringUtil.hasContent(loadPath)) {
            loadPath = DEFAULT_LOADPATH;
        }
        return loadPath;
    }
    public static void setLoadPath(String loadPath) {
        HttpUtil.loadPath = loadPath;
    }

    // 文件控件默认

    /**
     * 建立一个连接
     * @param urlStr 请求地址
     * @param method 请求的方式 ( 默认为 POST 或 GET )
     * @param method 请求的方式 ( 默认为 POST 或 GET )
     * @param contentType 服务端接受的请求数据
     * @throws IOException
     */
    public static HttpURLConnection createConnection(String urlStr, String method, String contentType)
            throws IOException {
        URL url = new URL(urlStr);
        // 获取对应的连接对象
        // 注意此时仅建立连接，并没有真正的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置 Http 请求头的一部分数据
        connection.setRequestMethod(method.toUpperCase());
        connection.setRequestProperty("Accept", "application/json;charset=utf-8;");
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setConnectTimeout(30000);

        // 如果是 post 请求, 需要打开向 URL 中的输出流,
        // 因为 post 请求携带的数据以流的形式传递到客户端, 而并不是通过 URL 携带
        if ("post".equalsIgnoreCase(method)) {
            // POST 请求时, 设置其 input 和 output
            // input 标识可以获取数据 ( 从客户端通过 InputStream, 从服务器端读取数据 )
            // output 标识可以输出数据 ( 在客户端通过 OutputStreamm, 将数据写入 URL, 发送请求 )
            connection.setDoInput(true);
            connection.setDoOutput(true);
        }

        return connection;
    }


    /**
     * 将 参数 通过 输出流 发送给服务端
     * @param connection 连接对象
     * @param paramStr 格式化后的参数
     * @throws IOException
     */
    public static void sendParamToUrl(HttpURLConnection connection, String paramStr)
            throws IOException {
        OutputStreamWriter postOut = null;

        try {
            // 如果有参数, 向 URL 中输入请求参数
            if (null != paramStr && !paramStr.isEmpty()) {
                postOut = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                postOut.write(paramStr);
                postOut.flush();
                postOut.close();
            }
        }
        finally {
            ResourceUtil.closeResources(postOut);
        }
    }


    /**
     * 发送一个 POST 请求
     * @param urlStr 请求地址
     * @param paramJson 请求体参数信息，以 JSON 格式发送
     * @return
     */
    public static String post(String urlStr, String paramJson) {
        HttpURLConnection connection = null;
        BufferedReader inReader = null;
        StringBuilder result = new StringBuilder("");

        try {
            connection = createConnection(urlStr, "POST", "application/json;charset=utf-8;");
            sendParamToUrl(connection, paramJson);

            // 从 URL 中读取响应内容
            //               缓存字符流 <======== 字符流 ( 转换流 ) <========== 字节流
            inReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = new String();
            while ((line = inReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(inReader);
        }

        return result.toString();
    }


    /**
     * 发送一个 POST 请求, 发送参数据形式
     * @param urlStr
     * @param xFormUrlEncoded 请求体参数信息, Map传入, 以参数据 ( key1=value1&key2=value2... ) 形式请求接口
     * @return
     */
    public static String post(String urlStr, Map<String, String> xFormUrlEncoded) {
        StringBuilder result = new StringBuilder("");
        HttpURLConnection connection = null;
        String xFormParam = StringUtil.mergeMapToxFormUrlEncoded(xFormUrlEncoded);
        BufferedReader inReader = null;

        try {
            connection = createConnection(urlStr, "POST", "application/x-www-form-urlencoded;charset=utf-8;");
            sendParamToUrl(connection, xFormParam);

            inReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = new String("");
            while ((line = inReader.readLine()) != null) {
                result.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(inReader);
        }

        return result.toString();
    }


//    /**
//     * 发送一个 POST 请求, 发送参数据形式
//     * @param urlStr
//     * @param jsonStr 请求体参数信息, json 传入, 以参数据 ( key1=value1&key2=value2... ) 形式请求接口
//     * @return
//     */
//    public static String sendPost_xFormUrlEncoded(String urlStr, String jsonStr) {
//
//    }

    /**
     * 发送一个 GET 请求
     * @param urlStr 请求地址
     * @return
     */
    public static String get(String urlStr) {
        /**
         * 1. 读取对应 paramJson, 转换并拼接到, url 上
         */
        StringBuilder result = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader bufReader = null;

        try {
            connection = createConnection(urlStr, "GET", "application/json;charset=utf-8;");

            bufReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(bufReader);
        }

        return result.toString();
    }


    /**
     * 从数据流中，获取赋值到前端 <img> 标签 src 属性上直接展示图片的 base64 字段
     * 可以 <img src="data:img/png;base64,....."> 用于直接显示
     * @param imgIn 输入的图片流
     * @param base64 是否使用 base64 编码
     * @return
     */
    public static String imgDataUrl(InputStream imgIn, boolean base64) {
        // 返回的流对象
        StringBuilder imgUrl = new StringBuilder("");
        // 具体的二进制流
        byte[] imageBytes = IOUtil.bytesInStream(imgIn);
        // 对应的图片
        String dataType = IOUtil.imgTypeInBytes(imageBytes);


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


    /**
     * 上传请求中的文件
     * @param request 原生的 Http 请求方法
     * @param inputName <input> 上传控件中的控件名称 ( 默认填入 file )
     * @param loadPath 上传到服务器的路径
     */
    public static void uploadFile(HttpServletRequest request, String inputName, String loadPath) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        uploadFile(multiRequest, inputName, loadPath);
    }
    
    public static void uploadFile(MultipartHttpServletRequest multiRequest, String inputName, String loadPath) {
        List<MultipartFile> multipartFiles = multiRequest.getFiles(inputName);

        try {
            for (MultipartFile multipartFile : multipartFiles) {
                // 当该段请求没有文件内容时
                if (multipartFile.isEmpty()) {
                    continue;
                }

                IOUtil.copyFile(multipartFile.getInputStream(), loadPath);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void uploadFile(MultipartHttpServletRequest multiRequest, String inputName) {
        uploadFile(multiRequest, inputName, getLoadPath());
    }

    public static void uploadFile(MultipartHttpServletRequest multiRequest) {
        uploadFile(multiRequest, "file", getLoadPath());
    }


    /**
     * 在处理器方法中下载对应的文件
     * @param request 处理器方法传入的 Servlet 原生请求
     * @param response 处理器方法传入的 Servlet 原生响应
     * @param file 需要下载的文件对象
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file)
            throws UnsupportedEncodingException {
        // 拼接取得 contentRange 属性
        String contentRange = initContentRange(request, file);
        // 设置响应头
        initDownloadResponse(response, contentRange, file);
        // 将文件写入到响应中
        loadFileToResponse(request, response, file);
    }


    /**
     * 在处理器方法中在线显示对应的文件
     * @param request 处理器方法传入的 Servlet 原生请求
     * @param response 处理器方法传入的 Servlet 原生响应
     * @param file 需要下载的文件对象
     */
    public static void displayFile(HttpServletRequest request, HttpServletResponse response, File file) throws UnsupportedEncodingException {
        // 将文件转换为 pdf 然后在网页上在线显示
        File pdfFile = IOUtil.toPdf(file);
        // 拼接取得 contentRange 属性
        String contentRange = initContentRange(request, pdfFile);
        // 设置响应头
        initDisplayResponse(response, contentRange, pdfFile);
        // 将文件写入到响应中
        loadFileToResponse(request, response, pdfFile);
    }


    /**
     * 初始化下载情况下的响应信息
     * @param response 处理器方法传入的 Servlet 原生响应
     * @return
     */
    public static HttpServletResponse initDownloadResponse(HttpServletResponse response, String contentRange, File file) throws UnsupportedEncodingException {
        Long endPos = 0L;

        if (StringUtil.hasContent(contentRange)) {
            endPos = Long.parseLong(contentRange.split("/")[1]);
        }
        else {
            endPos = file.length();
        }

        // 设置响应的主要内容为二进制流
        response.setContentType("application/octet-stream");
        // 添加下载文件的标识字段
        // inline : 将文件内容直接显示在页面
        // attachment : 弹出对话框让用户下载
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        // 设置响应中下载文件的范围
        response.addHeader("Content-Range", contentRange);
        // 设置响应中下载文件的大小
        response.addHeader("Content-Length", String.valueOf(endPos));
        // 设置响应中接受数据的类型
        response.addHeader("Accept-Ranges", "bytes");

        return response;
    }


    /**
     * 初始化在线显示情况下的响应信息
     * @param response 处理器方法传入的 Servlet 原生响应
     * @param contentRange 通过 Request 信息拼接的响应中的 content-Range 属性
     * @return
     */
    public static HttpServletResponse initDisplayResponse(HttpServletResponse response, String contentRange, File file) throws UnsupportedEncodingException {
        Long endPos = 0L;

        if (StringUtil.hasContent(contentRange)) {
            endPos = Long.parseLong(contentRange.split("/")[1]);
        }
        else {
            endPos = file.length();
        }

        // 在线显示 PDF
        response.reset();
        response.setContentType(response.getContentType());
        response.addHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        // 设置响应中下载文件的范围
        response.addHeader("Content-Range", contentRange);
        // 设置响应中下载文件的大小
        response.addHeader("Content-Length", String.valueOf(endPos));
        // 设置响应中接受数据的类型
        response.addHeader("Accept-Ranges", "bytes");

        return response;
    }


    /**
     * 根据请求信息和文件拼接出对应的下载响应
     * @param request 操作的请求信息
     * @param file 操作的文件
     * @return
     */
    public static String initContentRange(HttpServletRequest request, File file) {
        // 获取请求中的 Range, 得到对应响应中的下载大小
        String rangeContent = request.getHeader("Range");
        Long startPos = 0L;
        Long endPos;
        String[] ranges = new String[2];

        if (StringUtil.hasContent(rangeContent)) {
            // 根据请求头的 Range 构造出响应的 Content-Range
            ranges = request.getHeader("Range").split("=")[1].split("-");
            startPos = Long.parseLong(ranges[0]);
            endPos = Long.parseLong(ranges[1]);
        }
        else {
            endPos = file.length();
        }

        String contentRange = "bytes " + startPos + "-" + (endPos - 1) + "/" + endPos;

        return contentRange;
    }


    /**
     * 读取对应的文件到响应中
     * @param request
     * @param response 处理器方法传入的 Servlet 原生响应
     * @param file 需要处理的文件对象
     */
    private static void loadFileToResponse(HttpServletRequest request, HttpServletResponse response, File file) {
        // 获取请求中的 Range, 得到对应响应中的下载大小
        String rangeContent = request.getHeader("Range");
        String[] ranges = new String[2];
        Long startPos = 0L;

        if (StringUtil.hasContent(rangeContent)) {
            // 根据请求头的 Range 构造出响应的 Content-Range
            ranges = request.getHeader("Range").split("=")[1].split("-");
            startPos = Long.parseLong(ranges[0]);
        }

        byte[] buffer = new byte[1024];
        OutputStream out = null;
        InputStream in = null;

        try {
            // 用于向外写出数据
            out = response.getOutputStream();
            in = new FileInputStream(file);
            int readLength = 0;

            // 跳过已经下载的部分
            in.skip(startPos);

            while ((readLength = in.read(buffer)) != -1) {
                out.write(buffer, 0, readLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(in, out);
        }
    }
}