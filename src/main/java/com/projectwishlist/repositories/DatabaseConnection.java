package com.projectwishlist.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    static
    {
        String url = "jdbc:mysql://kea-wishlist.mysql.database.azure.com";
        String user = "wishlistadmin@kea-wishlist";
        String pass = "Keadb2022";
        try {
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return connection;
    }


}