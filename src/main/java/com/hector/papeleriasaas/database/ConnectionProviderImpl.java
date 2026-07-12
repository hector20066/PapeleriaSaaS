package com.hector.papeleriasaas.database;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProviderImpl implements ConnectionProvider {

    private final DataBasePool dataBase;

    public ConnectionProviderImpl(DataBasePool dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataBase.getConnection();
    }

}
