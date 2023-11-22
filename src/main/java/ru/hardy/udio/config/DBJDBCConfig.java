package ru.hardy.udio.config;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;

//@Configuration
public class DBJDBCConfig{

//    @Bean
//    JdbcTemplate jdbcSRZTemplate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
//        // extract this 4 parameters using your own logic
//        return new JdbcTemplate(da);
//    }

    public Statement getSRZ() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://srz\\srz;databaseName=srz3_00");
        dataSource.setUsername("expert");
        dataSource.setPassword("123");

        try {
            Connection connection = dataSource.getConnection();
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

    public Statement getUDIO() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://192.168.2.157/udio");
        dataSource.setUsername("mcherchesov");
        dataSource.setPassword("123");
//        dataSource.setUrl("jdbc:postgresql://localhost:5433/testudio");
//        dataSource.setUsername("hardy");
//        dataSource.setPassword("Byntuhf84");

        try {
            Connection connection = dataSource.getConnection();
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DataSource getBarsDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://192.168.2.210/d3_tfoms");
        dataSource.setUsername("rsotfoms_user");
        dataSource.setPassword("JgE2D43ddHUZSwcQ");
        return dataSource;
    }

    public DataSource getUDIODataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://192.168.2.157/udio");
        dataSource.setUsername("mcherchesov");
        dataSource.setPassword("123");
        return dataSource;
    }

    public DataSource getSRZDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://srz\\srz;databaseName=srz3_00");
        dataSource.setUsername("expert");
        dataSource.setPassword("123");
        return dataSource;
    }
}