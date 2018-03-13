/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
@Named(value = "store")
public class ToDoStore {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<ToDo> findAll(){
        return em.createNamedQuery(ToDo.FIND_ALL,ToDo.class)
                .getResultList();
    }
}
