package br.edu.ifsp.ingresso.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://192.168.0.11:3306/turtletickets", "ferdinando", "ferdinando");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
