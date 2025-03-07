/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author macbookpro2019
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1434; databaseName=SchoolDB_Java; encrypt=true; trustServerCertificate=true ";
    private static final String USER = "sa";
    private static final String PASSWORD = "vinn9502";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
            /*JOptionPane.showMessageDialog(null, "Connectin success!");*/
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

