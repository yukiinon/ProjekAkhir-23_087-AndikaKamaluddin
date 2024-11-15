/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projekakhir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author USEr
 */
public class koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_project";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        Connection kon = null;
        try {
            kon = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("terkoneksi");
        } catch (SQLException e) {
            System.out.println("error bang : " + e.getMessage());
        }
        return kon;
    }
    
    public static void main(String[] args) {
        getConnection();
    }
}
