package com.demo.JDBCUtil;

import com.zhq.util.JDBCUtil.DBConstant;
import com.zhq.util.JDBCUtil.DBUtil;
import com.zhq.util.JDBCUtil.SqlCondition;
import com.zhq.util.JsonUtil.JsonUtil;

import java.sql.ResultSet;

/**
 * SqlCondition 类的 Demo
 * @author Eddie Zhang
 */
public class SqlCondition_Demo {
    public static void main(String[] args) {
        SqlCondition_Demo instance = new SqlCondition_Demo();

        /**
         * 1. 初始化 DBUtil 类
         */
        instance.init_DBUtil();

        /**
         * 2. 调用对应的  demo 方法
         */
        instance.select_demo1();
        instance.select_demo2();
        instance.select_demo3();
        instance.select_demo4();

        instance.update_demo1();

        instance.delete_demo1();

        instance.insert_demo1();
    }

    public void init_DBUtil() {
        DBUtil.setUrl(DBConstant.URL_MYSQL("sys"));
        DBUtil.setUsername("root");
        DBUtil.setPassword("Gepoint");
    }

    // SELECT 使用 Demo

    /**
     * demo : select_col, from, where, lt, executedBy, release
     */
    public void select_demo1() {
        // 1. 建立查询实例
        SqlCondition selectSql = new SqlCondition();

        // 2. 使用链式写法构建 SqlCondition 实例
        // SQL: select *
        //        from course_1
        //            where c_id < 10
        selectSql
                .select_col("*")
                .from("course_1")
                .where().lt("c_id", 10);

        // 3. 使用 DataSource 调用对应的 SqlCondition, 得到原生的 ResultSet
        ResultSet queryResult = (ResultSet) selectSql.executedBy(DBUtil.getInnerDS());

        // 4. 输出结果
        System.out.println(JsonUtil.resultSetToJString(queryResult));

        // 5. 回收资源
        selectSql.release();
    }

    /**
     * demo : select_col, from, where, lt, eq, executedBy
     */
    public void select_demo2() {
        SqlCondition selectSql = new SqlCondition();
        // 在 where 条件函数中使用 SQL_AND | SQL_OR, 来拼接与前面条件的关系 ( 与 | 或 )
        // 注意 : or(), and() 并没有实际作用，只是用于增加可读性
        // SQL : select cname
        //         from course_1
        //           where c_id < user_id and cstatus='4144'
        selectSql
                .select_col("cname")
                .from("course_1")
                .where()
                .lt("c_id", "user_id").eq(DBConstant.SQL_AND, "cstatus", "'4144'");
        ResultSet queryResult = (ResultSet) selectSql.executedBy(DBUtil.getInnerDS());
        System.out.println(JsonUtil.resultSetToJString(queryResult));
        selectSql.release();
    }

    /**
     * demo : select_col, from, where, eq, page, executedBy, release
     */
    public void select_demo3() {
        SqlCondition selectSql = new SqlCondition();
        // 使用 page 来对查询的结果进行分页
        // 使用 left_join, right_join 等进行表的连接
        selectSql
                .select_col("*")
                .from("course_1 c1")
                .where().eq("c1.cname", "'p999'")
                .page(1, 2);
        ResultSet queryResult = (ResultSet) selectSql.executedBy(DBUtil.getInnerDS());
        System.out.println(JsonUtil.resultSetToJString(queryResult));
        selectSql.release();
    }

    /**
     * demo : select_col, from, where, eq, executedBy, release
     */
    public void select_demo4() {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    SqlCondition selectSql = new SqlCondition();
                    selectSql
                            .select_col("*")
                            .from("course_1 c1")
                            .where().eq("c1.cname", "'p999'");
                    ResultSet queryResult = (ResultSet) selectSql.executedBy(DBUtil.getInnerDS());
                    System.out.println(JsonUtil.resultSetToJString(queryResult));

                    selectSql.release();
                }
            });

            thread.start();
        }
    }

    /**
     * demo : select_col, from
     */
    public void demo5() {
        SqlCondition selectSql = new SqlCondition();
        selectSql
                .select_col(" * ")
                .from("course_1 c1");
        selectSql.release();
    }


    /**
     * demo : update_table, set_col, values, where, eq, executedBy
     */
    public void update_demo1() {
        SqlCondition updateSql = new SqlCondition();
        // SQL: update course_1
        //        set cstatus='update_demo1' and user_id=438
        //            where c_id=41
        updateSql
                .update_table("course_1")
                .set_col("cstatus", "user_id")
                .values("'update_demo1'", 438)
                .where().eq("c_id", 41);
        int updateCount = (Integer) updateSql.executedBy(DBUtil.getInnerDS());
        System.out.println(updateCount);
        updateSql.release();
    }


    /**
     * demo : delete_from, where, eq, where, executedBy, release
     */
    public void delete_demo1() {
        SqlCondition deleteSql = new SqlCondition();
        // SQL: delete from course_1 c1 where c1.c_id=41
        deleteSql.delete_from("course_1").where().eq("c_id", 41);
        boolean deleteCount = (Boolean) deleteSql.executedBy(DBUtil.getInnerDS());
        System.out.println(deleteCount);
        deleteSql.release();
    }


    /**
     * demo : insert_into, values, executedBy, release
     */
    public void insert_demo1() {
        SqlCondition insertSql = new SqlCondition();
        // SQL: insert into course_1 values(41, 'Bella', 450, 'update_deffa')
        insertSql.insert_into("course_1").values(41, "'Bella'", 450, "'update_ddfa'");
        boolean insertCount = (Boolean) insertSql.executedBy(DBUtil.getInnerDS());
        System.out.println(insertCount);
        insertSql.release();
    }
}
