package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection con;

    public static Connection getConnection() {
        try {
            // Check if the connection is already established
            if (con == null) {
                // Load MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish connection to the database
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Library",
                        "root",
                        "root"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}