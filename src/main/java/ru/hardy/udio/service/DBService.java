package ru.hardy.udio.service;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class DBService {

    private DriverManagerDataSource ConnectDB(String driver, String url, String user, String password) throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    public DataSource getd3_tfoms() throws SQLException {
        return ConnectDB(
                "org.postgresql.Driver",
                "jdbc:postgresql://192.168.2.210:5432/d3_tfoms",
                "rsotfoms_user",
                "JgE2D43ddHUZSwcQ"
        );
    }

    public DataSource getfoms() throws SQLException {
        return ConnectDB(
                "org.postgresql.Driver",
                "jdbc:postgresql://192.168.2.155:5432/foms",
                "fuser",
                "6PJyRMLH#Sf@tQLL9Sc@"
        );
    }

    public DataSource getSRZ() throws SQLException {
        return ConnectDB(
                "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                "jdbc:sqlserver://srz\\srz;databaseName=srz3_00",
                "sa",
                "123"
        );
    }
}
