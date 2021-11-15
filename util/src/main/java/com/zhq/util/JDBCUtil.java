package com.zhq.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    /**
     * 通过函数返回不同数据库的不同驱动
     *
     * @param dbname 数据库名称
     * @param ip     ip 地址
     * @param port   端口
     * @return
     */
    // mysql
    public static final String URL_MYSQL(String dbname, String ip, String port) {
        return "jdbc:mysql://" + ip + ":" + port + "/" + dbname + "?useSSL=false";
    }

    public static final String URL_MYSQL(String dbname, String ip) {
        return URL_MYSQL(dbname, ip, "3306");
    }

    // sqlserver
    public static final String URL_SQLSERVER(String dbname, String ip, String port) {
        return "jdbc:sqlserver://" + ip + ":" + port + "; DatabaseName=" + dbname;
    }

    public static final String URL_SQLSERVER(String dbname, String ip) {
        return URL_SQLSERVER(dbname, ip, "1433");
    }

    // oracle
    public static final String URL_ORACLE(String dbname, String ip, String port) {
        return "jdbc:oracle:thin:@" + ip + ":" + port + ":" + dbname;
    }

    public static final String URL_ORACLE(String dbname, String ip) {
        return URL_ORACLE(dbname, ip, "1521");
    }

    // db2
    public static final String URL_DB2(String dbname, String ip, String port) {
        return "jdbc:db2://" + ip + ":" + port + "/" + dbname;
    }

    public static final String URL_DB2(String dbname, String ip) {
        return URL_DB2(dbname, ip, "5000");
    }


//    /**
//     * 根据配置文件获取对应的数据源对象
//     * @param propertyFile 配置文件
//     * @return
//     */
//    public static DataSource druidDataSourceWithPropertiesFile(File propertyFile) {
//
//    }

    // 将文件插入对应的表中(Blob 类型)
    public static void insertFileToTable(File file, String tableName, String columnName, DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "insert into " + tableName + "(`" + columnName + "`) values(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        FileInputStream fIn = null;
        try {
            fIn = new FileInputStream(file);
            preparedStatement.setBlob(1, fIn);
            boolean inserted = preparedStatement.execute();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            ResourceUtil.closeResources(fIn);
        }
    }

    // 从表中读取对应的二进制数据(Blob 类型)
    public static void readFileFromTable(String tableName, String columnName, DataSource dataSource) {
        
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

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
