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
import javax.persistence.TypedQuery;

/**
 *
 * @author tss
 */
public class ToDoStore {

    EntityManagerFactory emf;
    EntityManager em;

    public ToDoStore() {
        emf = Persistence.createEntityManagerFactory("pu");
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
            em.merge(todo);
            tx().commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx().rollback();
        }
    }

    public void delete(Long id) {
        tx().begin();
        ToDo finded = em.find(ToDo.class, id);
        em.remove(finded);
        tx().commit();
    }

    public ToDo find(Long id) {
        return em.find(ToDo.class, id);
    }

    public List<ToDo> findAll() {
        return em.createNamedQuery(ToDo.FIND_ALL, ToDo.class)
                .getResultList();
    }

    public List<ToDo> findByDate(Date d) {
        return em.createNamedQuery(ToDo.FIND_BY_DATE, ToDo.class)
                .setParameter("p_data", d)
                .getResultList();
    }

}
