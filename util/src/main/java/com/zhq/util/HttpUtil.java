package com.zhq.util;

import com.alibaba.fastjson.JSONObject;
import jdk.internal.util.xml.impl.Input;
import org.springframework.http.HttpRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpUtil {
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


    public static byte[] receiveByteFromUrl() {
        byte[] receiveByte = new byte[1024];
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println(buffer.toString());

        return receiveByte;
    }

    public static String receiveStrFromUrl() {
        String receiveString = new String();

        return receiveString;
    }


    /**
     * 发送一个 POST 请求
     * @param urlStr 请求地址
     * @param paramJson 请求体参数信息，以 JSON 格式发送
     * @return
     */
    public static String sendPost_Json(String urlStr, String paramJson) {
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
    public static String sendPost_xFormUrlEncoded(String urlStr, Map<String, String> xFormUrlEncoded) {
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


    /**
     * 发送一个 POST 请求, 发送参数据形式
     * @param urlStr
     * @param jsonStr 请求体参数信息, json 传入, 以参数据 ( key1=value1&key2=value2... ) 形式请求接口
     * @return
     */
//    public static String sendPost_xFormUrlEncoded(String urlStr, String jsonStr) {
//
//    }


    /**
     * 发送一个 GET 请求
     * @param urlStr 请求地址
     * @return
     */
    public static String sendGet(String urlStr) {
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


    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file) {
        // 获取请求中的 Range, 得到对应响应中的下载大小
        String[] ranges = request.getHeader("Range").split("=")[1].split("-");
        // 根据请求头的 Range 构造出响应的 Content-Range
        Long startPos = Long.parseLong(ranges[0]);
        Long endPos = Long.parseLong(ranges[1]);
        String contentRange = "bytes " + startPos + "-" + (endPos - 1) + "/" + endPos;

//        二进制下载文件 ( 长度相关参参数 )
//        请求
//        Range: bytes=0-801
//        响应
//        Content-Range: bytes 0-800/801

        // 设置响应的主要内容为二进制流
        response.setContentType("application/octet-stream");
        // 添加下载文件的标识字段
        // inline / attachment inline : 将文件内容直接显示在页面
        // attachment : 弹出对话框让用户下载
        response.addHeader("Content-Disposition", "attachment;filename=");
        // 设置响应中下载文件的范围
        response.addHeader("Content-Range", contentRange);
        // 设置响应中下载文件的大小
        response.addHeader("Content-Length", String.valueOf(endPos));
        // 设置响应中接受数据的类型
        response.addHeader("Accept-Ranges", "bytes");


        byte[] buffer = new byte[1024];
        OutputStream out = null;
        InputStream in = null;

        try {
            // 用于向外写出数据
            out = response.getOutputStream();
            in = new FileInputStream(file);
            int readLength = 0;

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
