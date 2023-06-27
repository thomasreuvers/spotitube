package org.spotitube.Data.Context;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.spotitube.Domain.Exception.CustomException;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@ApplicationScoped
public class ConnectionContext implements IConnectionContext {

    @Override
    public Connection getConnection() {
        Properties properties = new Properties();
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

            return dataSource.getConnection();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new CustomException(e);
        }
    }
}
