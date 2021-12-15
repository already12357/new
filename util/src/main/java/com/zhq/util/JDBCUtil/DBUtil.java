package com.zhq.util.JDBCUtil;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Locale;


/**
 * 数据库的帮助类，可以快速生成静态数据源对象, 并执行查询语句
 * @author Eddie Zhang
 */
public class DBUtil {
    // 内部配置一个静态的数据源类
    private static DataSource innerDS;
    // 内置的一些数据源信息, 用于获取静态的数据类
    // 数据库类型
    private static String innerDbType = DBConstant.DB_MYSQL;
    // 连接池类型
    private static String innerPoolType = DBConstant.POOL_DRUID;
    // 数据库 URL
    private static String innerUrl = DBConstant.URL_MYSQL("sys", "127.0.0.1", "3306");
    // 连接数据库名称
    private static String innerDbname;
    // 登录数据库信息
    private static String innerUsername = "root";
    private static String innerPassword = "root";

    /**
     * 设置内部数据源的四大信息 ( username, password, url, poolType )
     */
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


    /**
     * 根据配置获取数据源
     */
    public static DataSource getInnerDS() {
        return innerDsWithConfig();
    }


    /**
     * 根据配置获取内部数据源
     * @return
     */
    private static DataSource innerDsWithConfig() {
        switch (innerPoolType) {
            case DBConstant.POOL_C3P0:
                return innerC3p0DataSource();

            case DBConstant.POOL_DBCP:
                return innerDbcpDataSource();

            // 默认返回类型为德鲁伊连接池的数据库
            default:
                return innerDruidDataSource();

        }
    }


    /**
     * 获取不同类型的数据库链接池对象 ( 通过类名反射调用 ), 如 ( dbcp, druid, c3p0.... )
     * @return
     */
    private static DataSource innerDbcpDataSource() {
        synchronized (DataSource.class) {
            if (null == innerDS) {
                try {
                    // 使用反射调用, 减少依赖引入
                    Class dsClazz = Class.forName(DBConstant.CLASS_DBCP_DS);
                    Method userSetter = dsClazz.getMethod("setUsername", String.class);
                    Method passwdSetter = dsClazz.getMethod("setPassword", String.class);
                    Method urlSetter = dsClazz.getMethod("setUrl", String.class);
                    Method driverSetter = dsClazz.getMethod("setDriverClassName", String.class);

                    innerDS = (DataSource) dsClazz.cast(dsClazz.newInstance());
                    userSetter.invoke(innerDS, innerUsername);
                    passwdSetter.invoke(innerDS, innerPassword);
                    urlSetter.invoke(innerDS, innerUrl);
                    driverSetter.invoke(innerDS, driverStrWithDbType(innerDbType));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return innerDS;
        }
    }
    private static DataSource innerC3p0DataSource() {
        synchronized (DataSource.class) {
            if (null == innerDS) {
                try {
                    // 使用反射调用, 减少依赖引入
                    Class dsClazz = Class.forName(DBConstant.CLASS_C3P0_DS);
                    Method userSetter = dsClazz.getMethod("setUser", String.class);
                    Method passwdSetter = dsClazz.getMethod("setPassword", String.class);
                    Method urlSetter = dsClazz.getMethod("setJdbcUrl", String.class);
                    Method driverSetter = dsClazz.getMethod("setDriverClass", String.class);

                    innerDS = (DataSource) dsClazz.cast(dsClazz.newInstance());
                    userSetter.invoke(innerDS, innerUsername);
                    passwdSetter.invoke(innerDS, innerPassword);
                    urlSetter.invoke(innerDS, innerUrl);
                    driverSetter.invoke(innerDS, driverStrWithDbType(innerDbType));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return innerDS;
        }
    }
    private static DataSource innerDruidDataSource() {
        synchronized (DataSource.class) {
            if (null == innerDS) {
                try {
                    // 使用反射调用, 减少依赖引入
                    Class dsClazz = Class.forName(DBConstant.CLASS_DRUID_DS);
                    Method userSetter = dsClazz.getMethod("setUsername", String.class);
                    Method passwdSetter = dsClazz.getMethod("setPassword", String.class);
                    Method urlSetter = dsClazz.getMethod("setUrl", String.class);
                    Method driverSetter = dsClazz.getMethod("setDriver", Driver.class);

                    innerDS = (DataSource) dsClazz.cast(dsClazz.newInstance());
                    userSetter.invoke(innerDS, innerUsername);
                    passwdSetter.invoke(innerDS, innerPassword);
                    urlSetter.invoke(innerDS, innerUrl);
                    driverSetter.invoke(innerDS, driverWithDBType(innerDbType));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
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
     * 重置内部数据源
     * @return
     */
    public static boolean resetInnerDs() {
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
     * 使用内部数据源执行对应的 SqlCondition 类
     * @param sqlCondition
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


}