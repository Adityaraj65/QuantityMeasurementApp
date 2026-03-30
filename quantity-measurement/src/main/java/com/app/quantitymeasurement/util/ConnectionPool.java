package com.app.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool instance;

    private final List<Connection> available = new ArrayList<>();
    private final List<Connection> used = new ArrayList<>();
    private final int maxSize;

    private ConnectionPool() {

        DatabaseConfig config = DatabaseConfig.getInstance();
        this.maxSize = config.getInt("db.pool.size");

        try {
            Class.forName(config.get("db.driver"));

            for (int i = 0; i < maxSize; i++) {
                available.add(createConnection());
            }

        } catch (Exception e) {
            throw new RuntimeException("Pool init failed", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private Connection createConnection() throws Exception {
        DatabaseConfig config = DatabaseConfig.getInstance();

        return DriverManager.getConnection(
                config.get("db.url"),
                config.get("db.username"),
                config.get("db.password")
        );
    }

    public synchronized Connection getConnection() {
        if (available.isEmpty()) {
            throw new RuntimeException("No DB connections available");
        }

        Connection conn = available.remove(available.size() - 1);
        used.add(conn);
        return conn;
    }

    public synchronized void release(Connection conn) {
        used.remove(conn);
        available.add(conn);
    }

    public String getStats() {
        return "Available=" + available.size() + ", Used=" + used.size();
    }
}