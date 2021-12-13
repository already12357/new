package com.zhq.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 常用正则表达式
     */
    public static final String REGEX_MAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public static final String REGEX_IPv4 = "(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))";


    /**
     * 根据正则表达式判断字符串是否有效
     */
    public static boolean isMailValid(String mail) {
        return Pattern.matches(REGEX_MAIL, mail);
    }
    public static boolean isIPv4Valid(String ipv4) {
        return Pattern.matches(REGEX_IPv4, ipv4);
    }


    /**
     * 判断字符串是否有内容
     * @param str 判断的字符串
     * @return
     */
    public static boolean hasContent(String str) {
        // 为空 或 字符串只有空格 时，视为没有内容
        return !(null == str || str.trim().length() == 0);
    }


    /**
     * 从 URL 格式的字符串中, 通过名称获取一个参数
     * @param url 输入的 URL 字符串
     * @param name 需要的参数名称
     * @return
     */
    public static String extractUrlParam(String url, String name) {
        int valueStart = url.indexOf(name + '=') + name.length() + 1;
        String paramParts = url.substring(valueStart);
        String[] values = paramParts.split("&");

        return values[0];
    }


    /**
     * 将 List<T> 对象转化为字符串然后拼接
     * @param list 需要拼接的 List<T>
     * @param regex 每个拼接元素之间的分隔符
     * @param <T>
     * @return
     */
    public static <T> String mergeList(List<T> list, String regex) {
        String mergeStr = "";

        if (null != list && !list.isEmpty()) {
            StringBuilder mergeStrBuilder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                T element = list.get(i);

                if (element != null && hasContent(element.toString())) {
                    mergeStrBuilder.append(element + regex);
                }
            }

            mergeStr = mergeStrBuilder.toString();

            if (mergeStr.length() > 0) {
                mergeStr = mergeStr.substring(0, mergeStr.length() - regex.length());
            }
        }

        return mergeStr;
    }



    /**
     * 将 Map 数据结构转化为参数据的形式
     * @param urlParamMap 对应的数据对
     * @return 返回转化的参数据形式
     */
    public static String mergeMapToUrlParams(Map<String, String> urlParamMap) {
        StringBuilder xFormUrlStrBuilder = new StringBuilder("");
        String xFormUrlStr = new String("");

        if (null != urlParamMap && !urlParamMap.isEmpty()) {
            for (Map.Entry<String, String> xForm : urlParamMap.entrySet()) {
                if (hasContent(xForm.getKey())) {
                    xFormUrlStrBuilder.append(xForm.getKey() + "=" + xForm.getValue() + "&");
                }
            }

            xFormUrlStr = xFormUrlStrBuilder.toString();
            xFormUrlStr = xFormUrlStr.substring(0, xFormUrlStr.length() - 1);
        }

        return xFormUrlStr;
    }


    /**
     * 替换字符串中的特定字符，并且使用空格在两边隔开
     * @param sourceStr 需要替换的源字符串
     * @param targetChar 字符串中对应的替换字符串
     * @param replaceContent 需要替换的内容
     * @param index 替换第几个字符串
     * @return
     */
    public static String replaceWithGap(String sourceStr, Character targetChar, String replaceContent, int index) {
        // 当字符串没有内容时
        if (!hasContent(sourceStr)) {
            return "";
        }

        String fullReplaceContent = new String(" ").concat(replaceContent).concat(" ");
        StringBuilder retStr = new StringBuilder(sourceStr);
        int replaceIndex = 0;

        // 遍历找寻第 n 个替换的内容
        for (int i = 0; i < index; i++) {
            replaceIndex = replaceContent.indexOf(targetChar, replaceIndex);
        }

        retStr.replace(replaceIndex, replaceIndex + 1, fullReplaceContent);

        return retStr.toString();
    }


    /**
     * 给对应的字符串打上单引号
     * @param str 目标字符串
     * @param isNullStr 为空时是否显示 null 字符串
     * @return
     */
    public static String quoted(String str, boolean isNullStr) {
        if (null == str) {
            if (isNullStr) {
                return "null";
            }
            else {
                return "";
            }
        }

        return new String("'").concat(str).concat("'");
    }

    public static String quoted(String str) {
        return quoted(str, false);
    }

    /**
     * 给所有字符串打上双引号
     * @param str 目标字符串
     * @param isNullStr 为空时是否显示 null 字符串
     * @return
     */
    public static String dQuoted(String str, boolean isNullStr) {
        if (null == str) {
            if (isNullStr) {
                return "null";
            }
            else {
                return "";
            }
        }

        return new String("\"").concat(str).concat("\"");
    }

    public static String dQuoted(String str) {
        return dQuoted(str, false);
    }
}
