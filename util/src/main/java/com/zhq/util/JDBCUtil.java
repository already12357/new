package com.zhq.util;

import com.sun.istack.internal.NotNull;

import javax.sql.DataSource;

public class JDBCUtil {
    /**
     * 通过函数返回不同数据库的不同驱动
     * @param name 数据库名称
     * @param ip   ip 地址
     * @param port 端口
     * @return
     */
    public static final String MYSQLURL(String name, String ip, String port) {
        return "jdbc:mysql://" + ip + ":" + port + "/" + name + "?useSSL=false";
    }
    public static final String MYSQLURL(String name, String ip) {
        return MYSQLURL(name, ip, "3306");
    }

    public static void writeFileToDB() {

    }
}
