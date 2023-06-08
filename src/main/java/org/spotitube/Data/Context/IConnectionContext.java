package org.spotitube.Data.Context;

import java.sql.Connection;

public interface IConnectionContext {
    Connection getConnection();
}
