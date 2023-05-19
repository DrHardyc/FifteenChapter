package ru.hardy.udio.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBJDBCConfig{
    public Statement getSRZ() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://srz\\srz;databaseName=srz3_00");
        dataSource.setUsername("expert");
        dataSource.setPassword("123");

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getBars() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://192.168.2.210/d3_tfoms");
        dataSource.setUsername("rsotfoms_user");
        dataSource.setPassword("JgE2D43ddHUZSwcQ");
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("select core.f_sys_set_config('sysuser',core.f_users8get_id_by_name('CherchesovMV')::text)");
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}