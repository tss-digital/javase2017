/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoapp.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author tss
 */
public class ToDoStore {

    EntityManagerFactory emf;
    EntityManager em;

    public ToDoStore() {
        emf = Persistence.createEntityManagerFactory("todo");
        em = emf.createEntityManager();
    }

    public EntityTransaction tx() {
        return em.getTransaction();
    }

    public void close() {
        em.close();
    }

    public void save(ToDo todo) {
        tx().begin();
        try {
            em.persist(todo);
            tx().commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx().rollback();
        }
    }

    public List<ToDo> findAll() {
        return em.createQuery("select e from ToDo e order by e.scadenza desc", ToDo.class)
                .getResultList();
    }

    public List<ToDo> findByDate(Date d) {
        return em.createQuery("select e from ToDo e where e.il= :data order by e.scadenza desc", ToDo.class)
                .setParameter("data", d)
                .getResultList();
    }

}
