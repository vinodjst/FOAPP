package com.fooderorder.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    //database connection
    static final String DB_URL = "jdbc:mysql://localhost/foodorder";
    static final String USER = "root";
    static final String PASS = "root";


       public static Connection getDbConnection() {

           Connection connection = null;
           try {
               connection = DriverManager.getConnection(DB_URL, USER, PASS);
               System.out.println("Connection established....!");

           } catch (SQLException e) {
               System.out.println("Error while connecting to DB..."+ e.getMessage());
               throw new RuntimeException(e.getMessage());
           }

           return connection;
       }


}
