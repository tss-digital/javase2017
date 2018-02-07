/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.dbmaven;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author tss
 */
public class APP {

    public static void main(String[] args) {

        DataSource ds = DsFactory.getMySQLDataSource();

        //Connessione aperta al dbms
        try( Connection cn = ds.getConnection()) {
      
            System.out.println("Connessione a DB ok......");

            //Creare ed inviare comandi al dbms
            Statement cmd = cn.createStatement();

            ResultSet rs = cmd.executeQuery("select first_name, last_name from actor order by last_name;");

            //Scorrere la tabella risultato
            while (rs.next()) {
                System.out.println(String.format("Cognome: %s , Nome: %s",
                        rs.getString("last_name"), rs.getString("first_name")));
                System.out.println("------------------------------------------");
            }

            rs.close();
            cmd.close();
            
        } catch (SQLException ex) {
            System.out.println("Ops...errore.." + ex.getMessage());
        }
    }
}
