package com.zhq.util.JDBCUtil;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zhq.util.ResourceUtil;
import com.mysql.jdbc.Driver;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;

public class JDBCUtil {
    // 内部配置一个静态的数据源类
    private static DataSource innerDS;
    // 内置的一些数据源信息, 用于获取静态的数据类
    // 数据库类型
    private static String innerDbType = ConstUtil.DB_MYSQL;
    // 连接池类型
    private static String innerPoolType = ConstUtil.POOL_DRUID;
    // 数据库 URL
    private static String innerUrl = ConstUtil.URL_MYSQL("sys", "127.0.0.1", "3306");
    // 连接数据库名称
    private static String innerDbname;
    // 登录数据库信息
    private static String innerUsername = "root";
    private static String innerPassword = "root";


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


//    private static DataSource ds;

//    static {
//        Properties properties = ResourceUtil.loadPropertiesFromResources("jdbc/datasource/druid.properties");

//        Properties prop = new Properties();
//        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
//        try {
//            prop.load(is);
//            ds = DruidDataSourceFactory.createDataSource(properties);
//        } catch (Exception e3) {
//            e3.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        return ds.getConnection();
//    }

//    public static Connection connectionInPool(DataSource dataSource) {
//        Connection connection = null;
//
//        try {
//            connection = dataSource.getConnection();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//
//        }
//        return dataSource.getConnection();
//    }

    public static void setUrl(String url) {
        innerUrl = url;
        innerDbType = getTypeByUrl(url);
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

    // 获取内部的数据源
    public static DataSource getInnerDS() {
        if (null == innerDS) {
            return innerDsWithConfig();
        }

        return innerDS;
    }

    /**
     * 根据内部的配置内容获取对应的连接池
     * @return
     */
    public static DataSource innerDsWithConfig() {
        switch (innerPoolType) {
            case ConstUtil.POOL_C3P0:
                return innerC3p0DataSource();

            case ConstUtil.POOL_DBCP:
                return innerDbcpDataSource();

            // 默认返回类型为德鲁伊连接池的数据库
            default:
                return innerDruidDataSource();

        }
    }

    /**
     * 获取不同类型的数据库链接池对象连接池对象
     * @return
     */
    public static DataSource innerDbcpDataSource() {
        synchronized (BasicDataSource.class) {
            if (null == innerDS) {
                innerDS = new BasicDataSource();

                ((BasicDataSource) innerDS).setUsername(innerUsername);
                ((BasicDataSource) innerDS).setPassword(innerPassword);
                ((BasicDataSource) innerDS).setUrl(innerUrl);
                ((BasicDataSource) innerDS).setDriverClassName(driverStrWithDbType(innerDbType));
            }

            return innerDS;
        }
    }
    public static DataSource innerC3p0DataSource() {
        synchronized (BasicDataSource.class) {
            if (null == innerDS) {
                innerDS = new ComboPooledDataSource();

                try {
                    ((ComboPooledDataSource) innerDS).setUser(innerUsername);
                    ((ComboPooledDataSource) innerDS).setPassword(innerPassword);
                    ((ComboPooledDataSource) innerDS).setJdbcUrl(innerUrl);
                    ((ComboPooledDataSource) innerDS).setDriverClass(driverStrWithDbType(innerDbType));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return innerDS;
        }
    }
    public static DataSource innerDruidDataSource() {
        synchronized (BasicDataSource.class) {
            if (null == innerDS) {
                innerDS = new DruidDataSource();

                ((DruidDataSource) innerDS).setUsername(innerUsername);
                ((DruidDataSource) innerDS).setPassword(innerPassword);
                ((DruidDataSource) innerDS).setUrl(innerUrl);
                ((DruidDataSource) innerDS).setDriver(driverWithDBType(innerDbType));
            }

            return innerDS;
        }
    }


    /**
     * 根据数据库类型获取对应的驱动类路径
     * @param dbType 传入的数据库类型
     * @return
     */
    public static String driverStrWithDbType(String dbType) {
        String formatDbType = dbType.toLowerCase(Locale.ENGLISH);

        switch (formatDbType) {
            case ConstUtil.DB_MYSQL:
                return ConstUtil.DRIVER_MYSQL_5;

            case ConstUtil.DB_ORACLE:
                return ConstUtil.DRIVER_ORACLE;

            case ConstUtil.DB_DB2:
                return ConstUtil.DRIVER_DB2;

            case ConstUtil.DB_SQLSERVER:
                return ConstUtil.DRIVER_SQLSERVER;

            default:
                return "";
        }
    }

    /**
     * 根据数据库类型加载对应的驱动类
     * @param dbType 传入的数据库类型
     * @return
     */
    public static Driver driverWithDBType(String dbType) {
        String formatDbType = dbType.toLowerCase(Locale.ENGLISH);

        try {
            switch (formatDbType) {
                case ConstUtil.DB_DB2:
                    return (Driver) Class.forName(ConstUtil.DRIVER_DB2).newInstance();

                case ConstUtil.DB_SQLSERVER:
                    return (Driver) Class.forName(ConstUtil.DRIVER_SQLSERVER).newInstance();

                case ConstUtil.DB_ORACLE:
                    return (Driver) Class.forName(ConstUtil.DRIVER_ORACLE).newInstance();

                default:
                    return (Driver) Class.forName(ConstUtil.DRIVER_MYSQL_5).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
            case ConstUtil.DB_REDIS:
            case ConstUtil.DB_MONGODB:
                return urlParts[0];

            // 关系型数据库类型
            case ConstUtil.JDBC_STR:
                return urlParts[2];

            default:
                return "";
        }
    }

    /**
     * 删除内部的数据源
     * @return
     */
    public static boolean clearInnerDs() {
        try {
            if (null != innerDS) {
                Class innerDSClazz = innerDS.getClass();
                Method closeMethod = innerDSClazz.getMethod("close");
                closeMethod.invoke(innerDS);
                innerDS = null;
            }

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 传入自定义的 SqlCondition 对象, 使用内部的数据源进行操作
     */
    public static void innerOperateSql(SqlCondition sqlCondition) {
        String sql = sqlCondition.generateSql();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getInnerDS().getConnection();

            switch (sqlCondition.getOpType()) {
                case ConstUtil.
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
