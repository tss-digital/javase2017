/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.employeefx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author tss
 */
public class ImpiegatoService {

    private final static String Q_UPDATE = "update employees "
            + "set firstName = ?, lastName=?, email=?, jobTitle=?, officeCode=?, reportsTo=? "
            + "where employeeNumber = ?";
    private final static String Q_INSERT = "insert into employees "
            + "(firstName,lastName,email,jobTitle,officeCode,reportsTo,employeeNumber ) values (?,?,?,?,?,?,?)";
    private final DataSource ds;

    public ImpiegatoService() {
        this.ds = DB.getMySQLDataSource();
    }

    public void save(Impiegato imp) {
        try (Connection cn = ds.getConnection();) {
            PreparedStatement cmd = imp.getNumero() == null
                    ? cn.prepareStatement(Q_INSERT)
                    : cn.prepareStatement(Q_UPDATE);
            cmd.setString(1, imp.getNome());
            cmd.setString(2, imp.getCognome());
            cmd.setString(3, imp.getEmail());
            cmd.setString(4, imp.getMansione());
            cmd.setString(5, imp.getUfficio());
            cmd.setInt(6, imp.getResponsabile() == null ? null : Integer.parseInt(imp.getResponsabile()));
            cmd.setInt(7, imp.getNumero() == null ? findEmployeeNumber() : imp.getNumero());
            cmd.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Errore durante il salvataggio dell'Impiegato", ex);
        }
    }

    public List<Impiegato> selezionaTutti() {
        try (Connection cn = ds.getConnection();) {
            List<Impiegato> result = new ArrayList<>();
            Statement cmd = cn.createStatement();
            ResultSet rs = cmd.executeQuery("select employeeNumber,firstName,lastName,email,officeCode,jobTitle,reportsTo from employees e order by e.lastName");
            while (rs.next()) {
                Impiegato imp = new Impiegato(rs.getInt("employeeNumber"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("officeCode"),
                        rs.getString("jobTitle"),
                        rs.getString("reportsTo")
                );
                result.add(imp);
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException("Errore durante la query selezionaTutti", ex);
        }

    }

    public Impiegato selezionaPerCodice(Integer employeeNumber) {
        if (employeeNumber == null) {
            return null;
        }
        try (Connection cn = ds.getConnection();) {
            Statement cmd = cn.createStatement();
            ResultSet rs = cmd.executeQuery("select employeeNumber,firstName,lastName,email,officeCode,jobTitle,reportsTo from employees e where employeeNumber= " + employeeNumber);
            return rs.next() ? new Impiegato(rs.getInt("employeeNumber"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("officeCode"),
                    rs.getString("jobTitle"),
                    rs.getString("reportsTo")
            ) : null;
        } catch (SQLException ex) {
            throw new RuntimeException("Errore durante la ricerca dell'impiegato per codice", ex);
        }
    }

    public void delete(Integer numero) {
        try (Connection cn = ds.getConnection();) {
            cn.setAutoCommit(false);
            Statement cmd = cn.createStatement();
            cmd.executeUpdate("update employees set reportsTo = null where reportsTo= " + numero);
            cmd.executeUpdate("update customers set salesRepEmployeeNumber=null where salesRepEmployeeNumber=" + numero);
            int executeResult = cmd.executeUpdate("delete from employees where employeeNumber= " + numero);
            cn.commit();
            System.out.println("Righe coinvolte: " + executeResult);
        } catch (SQLException ex) {
            throw new RuntimeException("Errore durante l'eliminazione dell'impiegato, ", ex);
        }
    }

    private int findEmployeeNumber() {
        try (Connection cn = ds.getConnection();) {
            Statement cmd = cn.createStatement();
            ResultSet rs = cmd.executeQuery("select max(employeeNumber) from employees");
            return rs.next() ? rs.getInt(1) + 1 : 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Errore durante la query selezionaTutti", ex);
        }
    }

}
