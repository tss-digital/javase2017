/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tss
 */
public class App {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Start --");
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection cn = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/sakila",
                         "root",
                         "mysql");
        
        System.out.println("Open Connection --");
        
        Statement cmd = cn.createStatement();
        ResultSet rs = cmd.executeQuery("SELECT * FROM actor order by last_name");
        
        while(rs.next()){
            System.out.println(rs.getString("last_name"));
        }
        
        cmd.close();
        cn.close();
    }
}
