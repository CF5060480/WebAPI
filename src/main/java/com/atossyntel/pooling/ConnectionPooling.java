/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atossyntel.pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionPooling {
     
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;
     
    public static ConnectionPooling create(String url, String user, String password) throws SQLException {
  
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPooling(url, user, password, pool);
    }

    private ConnectionPooling(String url, String user, String password, List<Connection> pool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = pool;
    }
     
     
    public Connection getConnection() {
        Connection connection = connectionPool
          .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }
     
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
     
    private static Connection createConnection(
      String url, String user, String password) 
      throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
     
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
 }
