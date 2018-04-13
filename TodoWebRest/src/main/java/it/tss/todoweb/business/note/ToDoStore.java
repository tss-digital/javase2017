/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.note;

import it.tss.todoweb.business.logging.Logged;
import it.tss.todoweb.business.utenti.Utente;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import it.tss.todoweb.business.security.AuthUser;

/**
 *
 * @author tss
 */
@Logged
@Stateless
public class ToDoStore {

    @Inject
    @AuthUser
    Utente authUser;
    
    @Context
    SecurityContext sc;
    
    @PersistenceContext
    private EntityManager em;

    
    public List<ToDo> findAll() {
        System.out.println(authUser);
        return em.createNamedQuery(ToDo.FIND_ALL, ToDo.class)
                .getResultList();
    }

    public List<ToDo> findToday() {
        return em.createNamedQuery(ToDo.FIND_BY_DATE)
                .setParameter("p_data", new Date())
                .getResultList();
    }

    public List<ToDo> find(Date d) {
        return em.createNamedQuery(ToDo.FIND_BY_DATE)
                .setParameter("p_data", d)
                .getResultList();
    }

    public ToDo find(long id) {
        return em.find(ToDo.class, id);
    }

    public List<ToDo> find(String word) {
        return em.createNamedQuery(ToDo.FIND_BY_WORD, ToDo.class)
                .setParameter("word", "%" + word + "%")
                .getResultList();
    }

    public void save(ToDo tosave) {
        em.merge(tosave);
    }

    public void delete(long id) {
        ToDo finded = find(id);
        em.remove(finded);
    }

}
