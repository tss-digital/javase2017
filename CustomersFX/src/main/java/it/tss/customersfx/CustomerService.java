/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.customersfx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author tss
 */
public class CustomerService {

    private DataSource ds;

    public CustomerService() {
        this.ds = DsFactory.getMySQLDataSource();
    }

    public List<Customer> findAll() {
        List<Customer> result = new ArrayList<>();
        try (Connection cn = ds.getConnection()) {
            Statement cmd = cn.createStatement();
            ResultSet rs = cmd.executeQuery("select customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,country from customers order by customerNumber ");
            while (rs.next()) {
                Customer c = new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                result.add(c);

            }
        } catch (SQLException ex) {
            throw new RuntimeException("Impossibile caricare i dati dei Clienti..", ex);
        }
        return result;
    }

}
