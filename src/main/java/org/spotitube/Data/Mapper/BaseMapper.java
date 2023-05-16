package org.spotitube.Data.Mapper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public abstract class BaseMapper<T> implements IBaseMapper<T> {

    // Initialize Connection
    protected Connection getConnection() {
        Properties properties = new Properties();
        Connection connection;
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            Class.forName(properties.getProperty("driver.class.name"));

            SQLServerDataSource dataSource = new SQLServerDataSource();
            dataSource.setUser(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setServerName(properties.getProperty("db.serverName"));
            dataSource.setPortNumber(Integer.parseInt(properties.getProperty("db.portNumber")));
            dataSource.setDatabaseName(properties.getProperty("db.databaseName"));
            dataSource.setTrustServerCertificate(true);

            connection = dataSource.getConnection();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
