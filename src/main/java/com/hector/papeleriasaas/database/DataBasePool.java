package com.hector.papeleriasaas.database;

import com.hector.papeleriasaas.utils.AppConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public final class DataBasePool {

    private static DataBasePool instance;
    private final HikariDataSource dataSource;

    private DataBasePool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(AppConfig.get("db.url"));
        config.setUsername(AppConfig.get("db.username"));
        config.setPassword(AppConfig.get("db.password"));
        config.setDriverClassName(AppConfig.get("db.driver"));

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);

        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);

        config.setValidationTimeout(5000);
        config.setKeepaliveTime(300000);

        config.setPoolName("Papelería");
        config.setAutoCommit(false);

        this.dataSource = new HikariDataSource(config);
    }

    public static synchronized DataBasePool getInstance() {
        if (instance == null) {
            instance = new DataBasePool();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

}
