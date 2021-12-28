package com.zhq.util;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUtil {
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
     * 将 List<String> 拼接为字符串
     * @param list 需要拼接的 List<String>
     * @param regex 每个拼接元素之间的分隔符
     * @return
     */
    public static String mergeList(List<String> list, String regex) {
        if (null == list) {
            return null;
        }

        StringBuilder mergeStr = new StringBuilder("");

        for (String element : list) {
            if (hasContent(element)) {
                mergeStr.append(element).append(regex);
            }
        }

        if (mergeStr.length() > 0) {
            return mergeStr.substring(0, mergeStr.length() - regex.length());
        }

        return "";
    }


    /**
     * 将字符串数组拼接为字符串
     * @param array 字符串数组
     * @param regex 每个拼接元素之间的分隔符
     * @return
     */
    public static String mergeArray(String[] array, String regex) {
        if (null == array) {
            return null;
        }

        StringBuilder mergeStr = new StringBuilder("");

        for (String element : array) {
            mergeStr.append(element).append(regex);
        }

        if (mergeStr.length() > 0) {
            return mergeStr.substring(0, mergeStr.length() - regex.length());
        }
        return "";
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
        int tempIndex = 0;

        // 遍历找寻第 n 个替换的内容
        for (int i = 0; i <= index; i++) {
            tempIndex = retStr.toString().indexOf(targetChar, tempIndex + 1);

            if (replaceIndex == -1) {
                retStr.replace(replaceIndex, replaceIndex + 1, fullReplaceContent);
                return retStr.toString();
            }

            replaceIndex = tempIndex;
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


    /**
     * 截去字符串末尾的字符 ( 逗号, 空格等 )
     * @param str 需要操作的字符
     * @param trimChar 需要取出的尾部字符
     * @return
     */
    public static String trimTailChar(String str, char trimChar) {
        if (!hasContent(str.trim())) {
            return "";
        }

        String trimStr = str.trim();

        if (trimStr.charAt(trimStr.length() - 1) == trimChar) {
            StringBuilder retStr = new StringBuilder(trimStr);
            retStr.deleteCharAt(trimStr.length() - 1);
            return retStr.toString();
        }

        return trimStr;
    }

    public static String trimTailComma(String str) {
        return trimTailChar(str, ',');
    }

    public static String trimTailBlank(String str) {
        return trimTailChar(str, ' ');
    }
}
