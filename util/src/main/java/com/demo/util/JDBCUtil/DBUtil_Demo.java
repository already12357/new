package com.demo.util.JDBCUtil;

import com.zhq.util.JDBCUtil.DBConstant;
import com.zhq.util.JDBCUtil.DBUtil;
import com.zhq.util.JDBCUtil.SqlCondition;
import com.zhq.util.JsonUtil.JsonUtil;

import javax.sql.DataSource;
import java.sql.ResultSet;

/**
 * DBUtil 类的 Demo
 * @author Eddie Zhang
 */
public class DBUtil_Demo {
    public static void main(String[] args) {
        DBUtil_Demo instance = new DBUtil_Demo();

        instance.demo1();
    }

    /**
     * demo : setUrl, setUsername, setPassword, getInnerDS, druidDataSource, executeSqlCondition,
     *        resetInnerDs, resetDs
     */
    public void demo1() {
        /**
         * 1. 初始化连接类
         * 设置 url, username, password 三大属性
         */
        DBUtil.setUrl(DBConstant.URL_MYSQL("sys"));
        DBUtil.setUsername("root");
        DBUtil.setPassword("Gepoint");

        /**
         * 2. 获取内部的数据库连接池
         *    或 直接传入信息创建
         */
//        DataSource innerDs = DBUtil.getInnerDS();
        DataSource outerDruidDs = DBUtil.druidDataSource(DBConstant.URL_MYSQL(""), "root", "Gepoint", DBConstant.DB_MYSQL);

        /**
         * 3. 使用数据源进行操作, 详细见 SqlCondition_Demo
         */
        SqlCondition selectSql = new SqlCondition();
        selectSql.select_col("*").from("course_1").where().eq("c_id", 41);
        // 使用 executeSqlCondition 执行自定义的查询对象
        ResultSet resultSet = (ResultSet) DBUtil.executeSqlCondition(selectSql);
        System.out.println(JsonUtil.resultSetToJString(resultSet));
        selectSql.release();

        /**
         * 4. 通过 resetDs 关闭数据连接池
         *    通过 resetInnerDs 关闭内部数据库连接池
         */
//        DBUtil.resetInnerDs();
        DBUtil.resetDs(outerDruidDs);
    }
}
