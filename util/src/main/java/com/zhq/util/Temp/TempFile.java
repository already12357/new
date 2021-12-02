package com.zhq.util.Temp;

// 用于存放工具类未整理的额外代码
public class TempFile {
// DBUtil.java
// =====================================================================================
//    /**
//     * 根据配置文件获取对应的数据源对象
//     * @param propertyFile 配置文件
//     * @return
//     */
//    public static DataSource druidDataSourceWithPropertiesFile(File propertyFile) {
//        DataSource dataSource = null;
//        Properties prop = new Properties();
//        InputStream fin = null;
//
//        try {
//            fin = new FileInputStream(propertyFile);
//            prop.load(fin);
//            dataSource = DruidDataSourceFactory.createDataSource(prop);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            ResourceUtil.closeResources(fin);
//        }
//
//        return dataSource;
//    }

//    public static DataSource druidDataSourceWithProperties(Properties properties) {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.configFromPropety(properties);
//        return dataSource;
//    }

//    // 将文件插入到对应的表中(Blob 类型)
//    public static boolean insertFileToTable(File file, String tableName, String columnName, DataSource dataSource) {
//        String sql = "insert into " + tableName + "(`" + columnName + "`) values(?)";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        FileInputStream fIn = null;
//        boolean inserted = false;
//
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement(sql);
//
//            fIn = new FileInputStream(file);
//            preparedStatement.setBlob(1, fIn);
//            inserted = preparedStatement.execute();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ResourceUtil.closeResources(fIn);
//        }
//
//        return inserted;
//    }


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
}
