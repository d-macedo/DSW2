package br.edu.ifsp.ingresso.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/turtletickets", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
