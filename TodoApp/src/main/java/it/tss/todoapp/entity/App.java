/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoapp.entity;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tss
 */
public class App {
    
    public static void main(String[] args) {
        
        LocalDate data = LocalDate.of(2018, Month.MARCH, 14);
        ToDo todo = new ToDo("", "",DateUtils.asDate(data) );
        save(todo);
        System.exit(0);
    }

    public static void save(ToDo todo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(todo);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    
}
