package com.zhq;

import com.alibaba.druid.util.JdbcUtils;
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
        DataSource dataSource = DBUtil.getInnerDS();
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
        DataSource dataSource = DBUtil.getInnerDS();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            DBUtil.resetInnerDs();
            dataSource = DBUtil.getInnerDS();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSqlCondition() throws SQLException {
        DBUtil.setUrl(DBConstant.URL_MYSQL("sys"));
        DBUtil.setUsername("root");
        DBUtil.setPassword("Gepoint");
        DBUtil.setPoolType(DBConstant.POOL_DRUID);

        DataSource innerDS = DBUtil.getInnerDS();

//        SqlCondition sqlInsert = new SqlCondition();
//
//        sqlInsert.toInsert()
//                .tables("course_1")
//                .values(7, "Eddie_Zhang", 61561, "1561");
//
//        sqlInsert.insert_into("course_1").values(7, "Eddie_Zhang", 61561, "1561");
//        String sql = sqlInsert.generateSql();

//        DBUtil.innerInsertSql(sqlInsert);
//        DBUtil.executeSqlCondition(sqlInsert);

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

//        SqlCondition sqlUpdate = new SqlCondition();
//        List<Object> rangeList = new ArrayList<>();
//        rangeList.add("4");
//        rangeList.add("7");
//        sqlUpdate.toUpdate().tables("course_1").columns("cname").values("c_name_change").in("cstatus", rangeList);
////        int updateBatch = DBUtil.innerUpdateSql(sqlUpdate);
//        int updateBatch = (int) DBUtil.executeSqlCondition(sqlUpdate);
//        System.out.println(updateBatch);


//        SqlCondition sqlDelete = new SqlCondition();
//        sqlDelete.toDelete().tables("course_1").eq("c_id", 7);
//        DBUtil.innerDeleteSql(sqlDelete);
//        DBUtil.executeSqlCondition(sqlDelete);


        // 增
        SqlCondition insertCondition = new SqlCondition();
        insertCondition.insert_into("course_1").values(7, "5f4d5f", 1645, "PPP");
        System.out.println(insertCondition.executedBy(innerDS));


        // 查
        SqlCondition selectCondition = new SqlCondition();
        selectCondition.select_col("*")
                .from("course_1")
                .where().between("c_id", 9, 14);

        ResultSet resultSet = null;

        try {
            resultSet = (ResultSet) selectCondition.executedBy(innerDS);

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1));
                System.out.print(resultSet.getInt(2));
                System.out.print(resultSet.getString(3));
                System.out.println(resultSet.getInt(4));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // 改
        SqlCondition updateCondition = new SqlCondition();
        updateCondition.update_table("course_1").set_col("cname").values("p999").where().lt("c_id", 11);
        System.out.println(updateCondition.executedBy(innerDS));


        // 查



        // 删
        SqlCondition deleteCondition = new SqlCondition();
        deleteCondition.delete_from("course_1").where().eq("c_id", 7);
        System.out.println(deleteCondition.executedBy(innerDS));;












//        chCondition.insert_into().columns("", "", false).values();
//        chCondition.update_table().set_values().where();
//        chCondition.select_col().from().where().groupby().having().orderBy().pageSize();
//        chCondition.delete_from().where();

//        pCondition.toSelect().columns("*").from().tables("course_1 c").in("c.c_id", );
    }
}
