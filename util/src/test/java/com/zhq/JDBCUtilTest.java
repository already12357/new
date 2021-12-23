package com.zhq;

import com.alibaba.druid.util.JdbcUtils;
import com.zhq.util.JDBCUtil.DBConstant;
import com.zhq.util.JDBCUtil.DBUtil;
import com.zhq.util.JDBCUtil.RowData;
import com.zhq.util.JDBCUtil.SqlCondition;
import com.zhq.util.JsonUtil;
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

        // 增
        SqlCondition insertCondition = new SqlCondition();
        insertCondition.insert_into("course_1").values(7, "5f4d5f", 1645, "PPP");
        System.out.println(insertCondition.executedBy(innerDS));

        // 查
        // 分页语法错误
        // error .......
        // ...........
        SqlCondition selectCondition = new SqlCondition();
        selectCondition.select_col("*")
                .from("sys.course_1").page(0, 8);
        System.out.println(selectCondition.generateSql());

        ResultSet resultSet = null;

        try {
            resultSet = (ResultSet) selectCondition.executedBy(innerDS);
            System.out.println(JsonUtil.jResultSetToJson(resultSet));

            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1));
                System.out.print(resultSet.getString(2));
                System.out.print(resultSet.getInt(3));
                System.out.println(resultSet.getString(4));
            }

            resultSet.first();

            List<RowData> rowDatas = RowData.valueOf(resultSet);
            for (RowData rowData : rowDatas) {
                System.out.println(rowData.get("c_id"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // 改
        SqlCondition updateCondition = new SqlCondition();
        updateCondition.update_table("course_1").set_col("cname").values("p999")
                .where()
                .lt("c_id", 11);
        System.out.println(updateCondition.executedBy(innerDS));


        // 查
        SqlCondition select2Condition = new SqlCondition();
        select2Condition.select_col("*")
                .from("course_1")
                .where()
                .between("c_id", 9, 14)
                .eq("c_id", "select c2.c_id from course_1 c2 where c2.c_id=9");
        ResultSet resultSet2 = null;

        try {
            resultSet2 = (ResultSet) select2Condition.executedBy(innerDS);

            while (resultSet2.next()) {
                System.out.print(resultSet2.getInt(1));
                System.out.print(resultSet2.getString(2));
                System.out.print(resultSet2.getInt(3));
                System.out.println(resultSet2.getString(4));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        SqlCondition deleteCondition = new SqlCondition();
        deleteCondition.delete_from("course_1").where().eq("c_id", 7);
        System.out.println(deleteCondition.executedBy(innerDS));






        SqlCondition selectTestLeftJoinTable = new SqlCondition();
        selectTestLeftJoinTable.select_col("*")
                .from("course_1").left_join("course_2").left_join("course_3", "course_3.c_id=course_3.c_id")
                .where().eq("cname", "p999")
                .like(DBConstant.SQL_AND, "cstatus", "%14%");
        System.out.println(selectTestLeftJoinTable.generateSql());
    }
}
