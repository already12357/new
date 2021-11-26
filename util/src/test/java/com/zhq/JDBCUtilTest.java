package com.zhq;

import com.zhq.util.JDBCUtil.ConstUtil;
import com.zhq.util.JDBCUtil.JDBCUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class JDBCUtilTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testExecuteSqlStorage() throws SQLException, IOException {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into filerepository(`attachguid`, `filedata`, `filetype`) values(?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        InputStream fIn = new FileInputStream(new File("C:/Users/Administrator/Desktop/gitRepository/util/src/main/resources/druid.properties"));

        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setBlob(2, fIn);
        preparedStatement.setString(3, ".properties");

        preparedStatement.execute();

        fIn.close();
        connection.close();
    }

    @Test
    public DataSource getDataBase() {
        // JDBC 中设置一个静态的配置, 用于获取对应的数据源
        JDBCUtil.setUrl();
        JDBCUtil.setDbname();
        JDBCUtil.setPassword();
        JDBCUtil.setUsername();
        JDBCUtil.setPoolType(ConstUtil.);
    }
}
