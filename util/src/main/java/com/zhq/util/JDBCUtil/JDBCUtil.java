package com.zhq.util.JDBCUtil;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zhq.util.ResourceUtil;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
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
    public static final String URL_MYSQL(String dbname) {
        return URL_MYSQL(dbname, "127.0.0.1", "3306");
    }

    // sqlserver
    public static final String URL_SQLSERVER(String dbname, String ip, String port) {
        return "jdbc:sqlserver://" + ip + ":" + port + "; DatabaseName=" + dbname;
    }
    public static final String URL_SQLSERVER(String dbname, String ip) {
        return URL_SQLSERVER(dbname, ip, "1433");
    }
    public static final String URL_SQLSERVER(String dbname) {
        return URL_SQLSERVER(dbname, "127.0.0.1", "1433");
    }

    // oracle
    public static final String URL_ORACLE(String dbname, String ip, String port) {
        return "jdbc:oracle:thin:@" + ip + ":" + port + ":" + dbname;
    }
    public static final String URL_ORACLE(String dbname, String ip) {
        return URL_ORACLE(dbname, ip, "1521");
    }
    public static final String URL_ORACLE(String dbname) {
        return URL_ORACLE(dbname, "127.0.0.1", "1521");
    }

    // db2
    public static final String URL_DB2(String dbname, String ip, String port) {
        return "jdbc:db2://" + ip + ":" + port + "/" + dbname;
    }
    public static final String URL_DB2(String dbname, String ip) {
        return URL_DB2(dbname, ip, "5000");
    }
    public static final String URL_DB2(String dbname) {
        return URL_DB2(dbname, "127.0.0.1", "5000");
    }


    /**
     * 根据配置文件获取对应的数据源对象
     * @param propertyFile 配置文件
     * @return
     */
    public static DataSource druidDataSourceWithPropertiesFile(File propertyFile) {
        Properties prop =
    }

    // 将文件插入到对应的表中(Blob 类型)
    public static boolean insertFileToTable(File file, String tableName, String columnName, DataSource dataSource) {
        String sql = "insert into " + tableName + "(`" + columnName + "`) values(?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FileInputStream fIn = null;
        boolean inserted = false;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            fIn = new FileInputStream(file);
            preparedStatement.setBlob(1, fIn);
            inserted = preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ResourceUtil.closeResources(fIn);
        }

        return inserted;
    }


//    // 从表中读取对应的二进制数据(Blob 类型)
//    public static InputStream readFileFromTable(String tableName, String columnName, DataSource dataSource) { }


    private static DataSource ds;

    static {
        Properties properties = ResourceUtil.loadPropertiesFromResources("jdbc/datasource/druid.properties");

//        Properties prop = new Properties();
//        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
//            prop.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
