package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.model.DBConnection;

public class TestDBConnection {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Connection successful!");
                String sql = "SELECT * FROM users";
                try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        System.out.println("User: " + resultSet.getString("username"));
                    }
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
