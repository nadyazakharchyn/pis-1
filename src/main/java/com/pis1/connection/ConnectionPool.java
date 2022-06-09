package com.pis1.connection;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final String url;
    private final String username;
    private final String password;
    private List<Connection> pool = new ArrayList();
    private List<Connection> usedConnections = new ArrayList();

    private static int POOL_SIZE = 20;

    private ConnectionPool() throws SQLException {
        Properties properties = new Properties();
        java.net.URL url = ClassLoader.getSystemResource("db.properties");
        try  {
            properties.load(url.openStream());
        } catch (FileNotFoundException file) {
            file.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");

        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(DriverManager.getConnection(this.url, username, password));
        }

    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(this.url, username, password);
    }

    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }


    public Connection getConnection() throws SQLException {
        if (pool.size() == 0) {
            pool.add(createConnection());
        }

        Connection connection = pool.remove(pool.size()-1);
        usedConnections.add(connection);

        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        pool.add(connection);
        while (pool.size() >= POOL_SIZE+1) {
            pool.remove(pool.size()-1);
        }
        return usedConnections.remove(connection);
    }

    public long amountOfFreeConnections() {
        return pool.size();
    }


}
