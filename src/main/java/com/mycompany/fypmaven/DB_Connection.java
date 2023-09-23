package com.mycompany.fypmaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Connection {
    
    
//    DB database details
    static String jdbcUrl = "jdbc:mysql://localhost:3306/passwordmanager"; 
    static String username = "root";
    static String password = "";

    static Connection connection;
   // Function to establish a MySQL database connection
    public static Connection getConnection() throws SQLException {
        
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the database connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC driver not found.");
        }
    }
    
    public String executeReadQuery(String query, Object[] params, String column) {

        ResultSet resultSet;
        String result = null;
        try ( Connection connection = getConnection()) {
            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getString(column);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

//  Function to execute query with parameters
    public void executeQuery(String query, Object[] params) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
                

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public String executeSelect(String query, Object[] params, String column) {

        ResultSet resultSet;
        String result = null;
        try ( Connection connection = getConnection()) {
            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getString(column);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
