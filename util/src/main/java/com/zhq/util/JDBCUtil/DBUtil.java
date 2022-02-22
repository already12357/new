package com.zhq.util.JDBCUtil;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;


/**
 * 数据库的帮助类，可以快速生成静态数据源对象, 并执行查询语句
 * 采用单例模式
 * @author Eddie Zhang
 */
public class DBUtil {
    // 内部的单例对象
    private static DBUtil instance = null;
    // 内部配置一个静态的数据源类
    private DataSource innerDS;
    // 内置的一些数据源信息, 用于获取静态的数据类
    // 数据库类型
    private String innerDbType = DBConstant.DB_MYSQL;
    // 连接池类型
    private String innerPoolType = DBConstant.POOL_DRUID;
    // 数据库 URL
    private String innerUrl = DBConstant.URL_MYSQL("sys", "127.0.0.1", "3306");
    // 连接数据库名称
    private String innerDbname;
    // 登录数据库信息
    private String innerUsername = "root";
    private String innerPassword = "root";

    // 私有化构造函数
    private DBUtil() {}
    
    // 对内部使用的开放函数
    private static DBUtil getInstance() {
        if (null == instance) {
            synchronized (DBUtil.class) {
                if (null == instance) {
                    instance = new DBUtil();
                }
            }
        }

        return instance;
    }
    
    /**
     * 设置内部数据源的四大信息 ( username, password, url, poolType )
     */
    public static void setUrl(String url) {
        getInstance().innerUrl = url;
        getInstance().innerDbType = getTypeByUrl(url);
    }
    public static void setPassword(String password) {
        getInstance().innerPassword = password;
    }
    public static void setUsername(String username) {
        getInstance().innerUsername = username;
    }
    public static void setPoolType(String poolType) {
        getInstance().innerPoolType = poolType;
    }


    /**
     * 根据配置获取内部数据源
     */
    public static DataSource getInnerDS() {
        return getInstance().innerDsWithConfig();
    }


    /**
     * 根据配置获取内部数据源
     * @return
     */
    private DataSource innerDsWithConfig() {
        switch (getInstance().innerPoolType) {
            case DBConstant.POOL_C3P0:
                return getInstance().innerC3p0DataSource();

            case DBConstant.POOL_DBCP:
                return getInstance().innerDbcpDataSource();

            // 默认返回类型为德鲁伊连接池的数据库
            default:
                return getInstance().innerDruidDataSource();

        }
    }

    /**
     * 获取不同类型的内部的数据库链接池对象 ( 通过类名反射调用 ), 如 ( dbcp, druid, c3p0.... )
     * @return
     */
    private DataSource innerDbcpDataSource() {
        synchronized (DataSource.class) {
            if (null == innerDS) {
                try {
                    innerDS = dbcpDataSource(innerUrl, innerUsername, innerPassword, innerDbType);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return innerDS;
        }
    }

    private DataSource innerC3p0DataSource() {
        synchronized (DataSource.class) {
            if (null == innerDS) {
                try {
                    innerDS = c3p0DataSource(innerUrl, innerUsername, innerPassword, innerDbType);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return innerDS;
        }
    }

    private DataSource innerDruidDataSource() {
        synchronized (DataSource.class) {
            if (null == innerDS) {
                try {
                    innerDS = druidDataSource(innerUrl, innerUsername, innerPassword, innerDbType);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return innerDS;
        }
    }


    /**
     * 供外部调用的数据库连接池创建函数，直接通过连接池的四个属性创建对应的数据库连接池
     * @param url
     * @param username
     * @param password
     * @param dbType
     * @return
     */
    public static DataSource dbcpDataSource(String url, String username, String password, String dbType) {
        try {
            DataSource ds = null;
            Class dsClazz = Class.forName(DBConstant.CLASS_DBCP_DS);
            Method userSetter = dsClazz.getMethod("setUsername", String.class);
            Method passwdSetter = dsClazz.getMethod("setPassword", String.class);
            Method urlSetter = dsClazz.getMethod("setUrl", String.class);
            Method driverSetter = dsClazz.getMethod("setDriverClassName", String.class);

            ds = (DataSource) dsClazz.cast(dsClazz.newInstance());
            userSetter.invoke(ds, username);
            passwdSetter.invoke(ds, password);
            urlSetter.invoke(ds, url);
            driverSetter.invoke(ds, driverStrWithDbType(dbType));

            return ds;
        }
        catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    public static DataSource dbcpDataSource(String url, String username, String password) {
        return dbcpDataSource(url, username, password, DBConstant.DB_MYSQL);
    }

    public static DataSource c3p0DataSource(String url, String username, String password, String dbType) {
        try {
            // 反射调用构建数据连接池
            DataSource ds = null;
            Class dsClazz = Class.forName(DBConstant.CLASS_C3P0_DS);
            Method userSetter = dsClazz.getMethod("setUser", String.class);
            Method passwdSetter = dsClazz.getMethod("setPassword", String.class);
            Method urlSetter = dsClazz.getMethod("setJdbcUrl", String.class);
            Method driverSetter = dsClazz.getMethod("setDriverClass", String.class);

            ds = (DataSource) dsClazz.cast(dsClazz.newInstance());
            userSetter.invoke(ds, username);
            passwdSetter.invoke(ds, password);
            urlSetter.invoke(ds, url);
            driverSetter.invoke(ds, driverStrWithDbType(dbType));
            return ds;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static DataSource c3p0DataSource(String url, String username, String password) {
        return c3p0DataSource(url, username, password, DBConstant.DB_MYSQL);
    }

    public static DataSource druidDataSource(String url, String username, String password, String dbType) {
        try {
            DataSource ds = null;
            // 反射调用构建数据连接池
            Class dsClazz = Class.forName(DBConstant.CLASS_DRUID_DS);
            Method userSetter = dsClazz.getMethod("setUsername", String.class);
            Method passwdSetter = dsClazz.getMethod("setPassword", String.class);
            Method urlSetter = dsClazz.getMethod("setUrl", String.class);
            Method driverSetter = dsClazz.getMethod("setDriver", Driver.class);

            ds = (DataSource) dsClazz.cast(dsClazz.newInstance());
            userSetter.invoke(ds, username);
            passwdSetter.invoke(ds, password);
            urlSetter.invoke(ds, url);
            driverSetter.invoke(ds, driverWithDBType(dbType));
            return ds;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static DataSource druidDataSource(String url, String username, String password) {
        return druidDataSource(url, username, password, DBConstant.DB_MYSQL);
    }


    /**
     * 根据数据库类型获取对应的驱动类路径
     * @param dbType 传入的数据库类型
     * @return
     */
    public static String driverStrWithDbType(String dbType) {
        String formatDbType = dbType.toLowerCase(Locale.ENGLISH);

        switch (formatDbType) {
            case DBConstant.DB_MYSQL:
                return DBConstant.DRIVER_MYSQL_5;

            case DBConstant.DB_ORACLE:
                return DBConstant.DRIVER_ORACLE;

            case DBConstant.DB_DB2:
                return DBConstant.DRIVER_DB2;

            case DBConstant.DB_SQLSERVER:
                return DBConstant.DRIVER_SQLSERVER;

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
                case DBConstant.DB_DB2:
                    return (Driver) Class.forName(DBConstant.DRIVER_DB2).newInstance();

                case DBConstant.DB_SQLSERVER:
                    return (Driver) Class.forName(DBConstant.DRIVER_SQLSERVER).newInstance();

                case DBConstant.DB_ORACLE:
                    return (Driver) Class.forName(DBConstant.DRIVER_ORACLE).newInstance();

                default:
                    return (Driver) Class.forName(DBConstant.DRIVER_MYSQL_5).newInstance();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据数据库 URL 提取对应的数据库类型
     * @param url 传入的数据库驱动 url
     * @return
     */
    public static String getTypeByUrl(String url) {
        String[] urlParts = url.split(":");

        switch (urlParts[0]) {
            // 非关系型数据库类型
            case DBConstant.DB_REDIS:
            case DBConstant.DB_MONGODB:
                return urlParts[0];

            // 关系型数据库类型
            case DBConstant.JDBC_STR:
                return urlParts[2];

            default:
                return "";
        }
    }


    /**
     * 重置内部数据源, 在关闭内部数据源后, 重新建立对应的数据源
     * @return
     */
    public static boolean resetInnerDs() {
        try {
            if (null != getInstance().innerDS) {
                Class innerDSClazz = getInstance().innerDS.getClass();
                Method closeMethod = innerDSClazz.getMethod("close");
                closeMethod.invoke(getInstance().innerDS);
                getInstance().innerDS = null;
            }

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean resetDs(DataSource dataSource) {
        try {
            if (null != dataSource) {
                Class dsClazz = dataSource.getClass();
                Method closeMethod = dsClazz.getMethod("close");
                closeMethod.invoke(dataSource);
                dataSource = null;
            }

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 使用内部数据源执行对应的 SqlCondition 类
     * @param sqlCondition 自定义的 SqlCondition 类型
     * @return
     */
    public static Object executeSqlCondition(SqlCondition sqlCondition) {
        if (null != sqlCondition) {
            try {
                Connection connection = getInnerDS().getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlCondition.generateSql());

                switch (sqlCondition.getOpType()) {
                    case DBConstant.SQL_DELETE:
                    case DBConstant.SQL_INSERT:
                        return ps.execute();

                    case DBConstant.SQL_UPDATE:
                        return ps.executeUpdate();

                    default:
                        return ps.executeQuery();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     * 将 ResultSet 对象转化为 List<Map> 对象
     * 每个 Map 对象存储一行数据
     * @param queryResult 查询的结果
     */
    public static List<Map<String, Object>> resultSetToList(ResultSet queryResult) {
        ResultSetMetaData metaData = null;

        try {
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

            metaData = queryResult.getMetaData();

            while (queryResult.next()) {
                Map<String, Object> rowMap = new HashMap<String, Object>();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = queryResult.getObject(i);
                    rowMap.put(columnName, columnValue);
                }

                resultList.add(rowMap);
            }

            return resultList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}