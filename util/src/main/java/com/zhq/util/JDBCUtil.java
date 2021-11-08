package com.zhq.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sun.istack.internal.NotNull;
import sun.applet.Main;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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




    private static DataSource ds;

    static {
        Properties prop = new Properties();
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");

        try {
            prop.load(is);
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public JDBCUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Statement stat, Connection conn) {
        close((ResultSet)null, stat, conn);
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static void close(ResultSet rs, Statement stat, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }

        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

    }
}
