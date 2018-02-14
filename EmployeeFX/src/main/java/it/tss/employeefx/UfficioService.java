/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.employeefx;

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
public class UfficioService {

    private final DataSource ds;

    public UfficioService() {
        this.ds = DB.getMySQLDataSource();
    }

    public List<Ufficio> selezionaTutti() {
        try (Connection cn = ds.getConnection();) {
            List<Ufficio> result = new ArrayList<>();
            Statement cmd = cn.createStatement();
            ResultSet rs = cmd.executeQuery("select officeCode,city from offices e order by e.city");
            while (rs.next()) {
                Ufficio uff = new Ufficio(rs.getString(1), rs.getString(2));
                result.add(uff);
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException("Errore durante la query selezionaTutti", ex);
        }

    }

    public Ufficio selezionaPerCodice(String codice) {
        try (Connection cn = ds.getConnection();) {
            Statement cmd = cn.createStatement();
            ResultSet rs = cmd.executeQuery("select officeCode,city from offices where officeCode= '" + codice + "'");
            return rs.next() ? new Ufficio(rs.getString(1), rs.getString(2)) : null;
        } catch (SQLException ex) {
            throw new RuntimeException("Errore durante la query selezionaTutti", ex);
        }

    }
}
