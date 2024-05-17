package com.example.vstocks_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static Connection connectDB (){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "kennyw");
            System.out.println("Connected to database");
            return connect;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver Not Found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection Failed.");
            e.printStackTrace();
        }
        return null;
    }
}
