package com.zhq.util.JDBCUtil;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zhq.util.ResourceUtil;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    // 内置的一些数据源信息, 用于获取静态的数据类
    // 数据库类型
    public static String innerDbType;
    // 连接池类型
    public static String innerPoolType;
    // 数据库 URL
    public static String innerUrl;
    // 连接数据库名称
    public static String innerDbname;
    // 登录数据库信息
    public static String innerPassword;
    public static String innerUsername;

    /**
     * 根据配置文件获取对应的数据源对象
     * @param propertyFile 配置文件
     * @return
     */
    public static DataSource druidDataSourceWithPropertiesFile(File propertyFile) {
        DataSource dataSource = null;
        Properties prop = new Properties();
        InputStream fin = null;

        try {
            fin = new FileInputStream(propertyFile);
            prop.load(fin);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ResourceUtil.closeResources(fin);
        }

        return dataSource;
    }

    public static DataSource druidDataSourceWithProperties(Properties properties) {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.configFromPropety(properties);

        return dataSource;
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
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static Connection connectionInPool(DataSource dataSource) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

        }
        return dataSource.getConnection();
    }

    public static void setUrl(String url) {
        innerUrl = url;
    }

    public static void setDbname(String dbname) {
        innerDbname = dbname;
    }

    public static void setPassword(String password) {
        innerPassword = password;
    }

    public static void setUsername(String username) {
        innerUsername = username;
    }

    public static void setPoolType(String poolType) {
        innerPoolType = poolType;
    }

    /**
     * 根据数据库驱动返回对应的数据库类型
     * @param url 传入的数据库驱动 url
     * @return
     */
    public static String getTypeByUrl(String url) {
        String[] urlParts = url.split(":");
        String retType = null;

        switch (urlParts[0]) {
            // 非关系型数据库类型
            case ConstUtil.STR_REDIS:
            case ConstUtil.STR_MONGODB:
                return urlParts[0];

            // 关系型数据库类型
            case ConstUtil.STR_JDBC:
                return urlParts[2];

            default:
                return "";
        }
    }


    public static DataSource dataSourceWithInnerConfig() {

    }

    public static DruidDataSource innerDruidDataSource() {
        DruidDataSource innerDS = new DruidDataSource();

        innerDS.setUsername(innerUsername);
        innerDS.setPassword(innerPassword);
        innerDS.setUrl(innerUrl);
        innerDS.setDriver(driverWithDBType(innerDbType));

        return innerDS;
    }

    /**
     * 根据数据库类型加载对应的数据包 ( 反射  )
     * @param dbType
     * @return
     */
    public static Driver driverWithDBType(String dbType) {
        Driver driver = null;
        Class.forName().newInstance();

        switch (dbType) {
            case ConstUtil.MYSQL_STR:
                break;

            case ConstUtil.MYSQL_STR:
                break;

            case ConstUtil.SQLSERVER_STR:
                break;

            case ConstUtil.
        }

        return driver;
    }
}
