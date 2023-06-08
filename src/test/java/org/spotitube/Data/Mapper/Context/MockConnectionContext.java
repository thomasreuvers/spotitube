package org.spotitube.Data.Mapper.Context;

import org.spotitube.Data.Context.IConnectionContext;

import java.sql.Connection;

public class MockConnectionContext implements IConnectionContext {
    private final Connection connection;

    public MockConnectionContext(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
