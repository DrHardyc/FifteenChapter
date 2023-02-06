package ru.hardy.udio.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBJDBCConfig{
    public Statement getSRZ() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://srz\\srz;databaseName=srz3_00");
        dataSource.setUsername("expert");
        dataSource.setPassword("123");
        Connection connection = dataSource.getConnection();
        return connection.createStatement();
    }
}