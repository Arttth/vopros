package org.arta.vopros.utils;

import org.arta.vopros.exception.ConnectionManagerOpenException;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static BlockingQueue<Connection> pool;

    private ConnectionManager() {}

    private static void initConnectionPool() {
        String propertiesPoolSize = PropertiesUtils.getProperty(POOL_SIZE_KEY);
        int poolSize = propertiesPoolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(propertiesPoolSize);

        pool = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; ++i) {
            Connection connection = open();
            Connection proxyConnection = (Connection) Proxy.newProxyInstance(
                    Connection.class.getClassLoader(),
                    new Class[] {Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy) :
                            method.invoke(connection, args)
                    );
            pool.add(proxyConnection);
        }
    }

    private static void initDatabaseDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        initDatabaseDriver();
        initConnectionPool();
    }

    public static Connection getConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtils.getProperty(URL_KEY),
                    PropertiesUtils.getProperty(USERNAME_KEY),
                    PropertiesUtils.getProperty(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new ConnectionManagerOpenException(e);
        }
    }
}