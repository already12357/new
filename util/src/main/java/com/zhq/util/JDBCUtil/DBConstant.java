package com.zhq.util.JDBCUtil;

// JDBC 常量类
public class DBConstant {
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



    // 各种数据库的标志缩写
    public static final String DB_MYSQL = "mysql";
    public static final String DB_ORACLE = "oracle";
    public static final String DB_SQLSERVER = "sqlserver";
    public static final String DB_DB2 = "db2";
    public static final String DB_MONGODB = "mongodb";
    public static final String DB_REDIS = "redis";
    public static final String JDBC_STR = "jdbc";
    
    // 数据库驱动类路径
    public static final String DRIVER_MYSQL_5 = "com.mysql.jdbc.Driver";
    public static final String DRIVER_MYSQL_8 = "com.mysql.cj.jdbc.Driver";
    public static final String DRIVER_DB2 = "";
    public static final String DRIVER_ORACLE = "";
    public static final String DRIVER_SQLSERVER = "";

    // 各种数据库连接池的缩写
    public static final String POOL_DRUID = "druid";
    public static final String POOL_C3P0 = "c3p0";
    public static final String POOL_DBCP = "dhcp";
    public static final String POOL_HIKARI = "hikari";

    // 数据库连接池类
    public static final String CLASS_DRUID_DS = "com.alibaba.druid.pool.DruidDataSource";
    public static final String CLASS_C3P0_DS = "com.mchange.v2.c3p0.ComboPooledDataSource";
    public static final String CLASS_DBCP_DS = "org.apache.commons.dbcp.BasicDataSource";
    public static final String CLASS_HIKARI_DS= "";

    // 数据库常用词
    public static final String SQL_SELECT = "SELECT";
    public static final String SQL_DELETE = "DELETE";
    public static final String SQL_UPDATE = "UPDATE";
    public static final String SQL_INSERT = "INSERT";
    public static final String SQL_AND = "AND";
    public static final String SQL_OR = "OR";


    // 数据库函数, 需要区分不同的数据库类型, 默认为 mysql
    // 待完成
    public static final String FUNCTION_CONCAT(String dbType, String...params) {
        StringBuilder contactStr = new StringBuilder("");

        if (params.length > 0) {
            switch (dbType) {
                case DBConstant.DB_MYSQL:
                    contactStr.append("CONCAT(");
                    for (String param : params) {
                        contactStr.append("'")
                                .append(param)
                                .append("',");
                    }
                    contactStr.append(")");
                    contactStr.deleteCharAt(contactStr.length() - 2);
                    break;

                case DBConstant.DB_ORACLE:
                    for (String param : params) {
                        contactStr.append("'")
                                .append(param)
                                .append("'")
                                .append("||");
                    }
                    break;

            }

        }

        return contactStr.toString().trim();
    }
    public static final String FUNCTION_POW(String dbType, Integer pow, Integer x) {
        StringBuilder contactStr = new StringBuilder("");



        return contactStr.toString().trim();
    }
    public static final String FUNCTION_CASE() {
        StringBuilder contactStr = new StringBuilder("");



        return contactStr.toString().trim();
    }
}
