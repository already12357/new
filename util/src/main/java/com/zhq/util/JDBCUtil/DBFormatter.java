package com.zhq.util.JDBCUtil;

/**
 * 用于处理不同种类 SQL 语言中的格式问题
 */
public class DBFormatter {
    /**
     * 根据不同的数据库类型，格式化列名表示
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
}
