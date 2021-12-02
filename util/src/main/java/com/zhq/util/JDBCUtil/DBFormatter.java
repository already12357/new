package com.zhq.util.JDBCUtil;

/**
 * 用于处理不同种类 SQL 语言中的格式问题
 */
public class DBFormatter {
    /**
     * 根据不同的数据库类型，格式化列名字符串
     * @param dbType 根据字符串常量判断数据库类型
     * @param originColumn 没有格式的列名
     * @return
     */
    public static String formatColumnName(String dbType, String originColumn) {
        switch (dbType) {


            default:
                return new String("`").concat(originColumn).concat("`");
        }
    }


    /**
     * 对传入的 Object 对象进行字符串的格式化
     * @param value 参数对象
     * @return
     */
    public static String formatObjToStr(Object value) {
        if (null == value) {
            return "null";
        }

        String valueStr = String.valueOf(value);

        // 判断传入的父类 value 是否为 String 类型, 如果是 String 类型，则添加双引号 
        if (value.getClass().isAssignableFrom(String.class)) {
            return new String("'").concat(valueStr).concat("'");
        }

        return valueStr;
    }
}
