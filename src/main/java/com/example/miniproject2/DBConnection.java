package com.example.miniproject2;

import java.sql.Connection;
import java.sql.DriverManager;

    public class DBConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/mini_project3";
        private static final String USER = "root";
        private static final String PASSWORD = "sqlrootpass33@bs/java";

        public static Connection getConnection() throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

