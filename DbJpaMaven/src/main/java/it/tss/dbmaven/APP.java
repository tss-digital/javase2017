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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author tss
 */
public class APP {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classicmod");

        EntityManager em = emf.createEntityManager();

        //List<Employees> result = em.createNamedQuery("Employees.findAll", Employees.class)
        //        .getResultList();
        List<Employees> result = em.createQuery(
                "select e from Employees e order by e.jobTitle",Employees.class)
                .getResultList();
        
        System.out.println("------------------------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        result.stream().forEach(System.out::println);

        Employees imp = new Employees(1800, "mario", "rossi", "mario@hotmail.com", "impiegato");
        
        Offices uff = em.find(Offices.class, "1");
        imp.setOfficeCode(uff);
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(imp);
        tx.commit();
        
        System.exit(0);
    }
}
