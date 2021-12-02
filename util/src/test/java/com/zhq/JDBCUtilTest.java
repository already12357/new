package com.zhq;

import com.zhq.util.JDBCUtil.DBConstant;
import com.zhq.util.JDBCUtil.DBUtil;
import com.zhq.util.JDBCUtil.SqlCondition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtilTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Test
//    public void testExecuteSqlStorage() throws SQLException, IOException {
//        Connection connection = JDBCUtil.getConnection();
//        String sql = "insert into filerepository(`attachguid`, `filedata`, `filetype`) values(?, ?, ?);";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        InputStream fIn = new FileInputStream(new File("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/druid.properties"));
//
//        preparedStatement.setString(1, UUID.randomUUID().toString());
//        preparedStatement.setBlob(2, fIn);
//        preparedStatement.setString(3, ".properties");
//
//        preparedStatement.execute();
//
//        fIn.close();
//        connection.close();
//    }

    @Test
    public void getDataBase() {
        // JDBC 中设置一个静态的配置, 用于获取对应的数据源
        DBUtil.setUrl(DBConstant.URL_MYSQL("sys", "127.0.0.1", "3306"));
        DBUtil.setUsername("root");
        DBUtil.setPassword("Gepoint");
        DataSource dataSource = DBUtil.innerDsWithConfig();
        Connection connection = null;
        SqlCondition sqlCondition = new SqlCondition();

        try {
            connection = dataSource.getConnection();
            String sql = "select 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            boolean isvalid = preparedStatement.execute();
            System.out.println(isvalid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void innerCheckTest() {
        DBUtil.setUrl(DBConstant.URL_MYSQL("sys", "127.0.0.1", "3306"));
        DBUtil.setUsername("root");
        DBUtil.setPassword("Gepoint");
        DataSource dataSource = DBUtil.innerDsWithConfig();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            DBUtil.clearInnerDs();
            dataSource = DBUtil.innerDruidDataSource();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSqlCondition() throws SQLException {
//        DBUtil.setUrl(DBConstant.URL_MYSQL("sys"));
//        DBUtil.setUsername("root");
//        DBUtil.setPassword("Gepoint");
//        DBUtil.setPoolType(DBConstant.POOL_C3P0);
//
//        DataSource innerDS = DBUtil.getInnerDS();
//
//        SqlCondition sqlInsert = new SqlCondition();
//        sqlInsert.toInsert()
//                .onColumn("c_id", "cname", "user_id")
//                .inTables("course_1")
//                .withValue(18, "Eddie_Zhang", 61561);
//        DBUtil.innerInsertSql(sqlInsert);


//        SqlCondition sqlQuery = new SqlCondition();
//        sqlQuery.toSelect()
//                .inTables("course_1")
//                .onColumn("c_id")
//                .eq("cstatus", null)
//                .between("c_id", 8, 11);
//
//        ResultSet resultSet = DBUtil.innerSelectSql(sqlQuery);
//
//        while (resultSet.next()) {
//            int read = resultSet.getInt(1);
//            System.out.println(read);
//        }
//
//        System.out.println(resultSet);

        SqlCondition sqlUpdate = new SqlCondition();
        List<Object> rangeList = new ArrayList<>();
        rangeList.add("4");
        rangeList.add("7");
        sqlUpdate.toUpdate().tables("course_1").columns("cname").values("c_name_change").in("cstatus", rangeList);
        int updateBatch = DBUtil.innerUpdateSql(sqlUpdate);
        System.out.println(updateBatch);

        SqlCondition sqlDelete = new SqlCondition();

    }
}
