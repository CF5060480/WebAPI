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
    private static ConnectionPooling instance;
    private ConnectionPooling() {
           //default constructor
    }
    
    static {
        instance = new ConnectionPooling();
    }
    
    public static ConnectionPooling getInstance() {
        return instance;
    }
 }
