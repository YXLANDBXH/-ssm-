package com.xl.test;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试dbcp数据库连接
 * @author XLong
 * @create 2021-07-16 13:12
 */
public class TestConnection {
    public static void main(String[] args) throws SQLException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/hrm");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        System.out.println(basicDataSource);
        Connection connection = basicDataSource.getConnection();
        System.out.println(connection);
    }
}
