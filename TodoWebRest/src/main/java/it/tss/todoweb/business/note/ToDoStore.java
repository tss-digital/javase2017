/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.note;

import it.tss.todoweb.business.DateUtils;
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
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author tss
 */
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
        return em.createNamedQuery(ToDo.FIND_ALL, ToDo.class)
                .setParameter("id_utente", authUser.getId())
                .getResultList();
    }

    public JsonArray findAllJson() {
        List<ToDo> result = em.createNamedQuery(ToDo.FIND_ALL, ToDo.class)
                .setParameter("id_utente", authUser.getId())
                .getResultList();
        
        return toJson(result);
    }

    public JsonArray findToday() {
       List<ToDo> result = em.createNamedQuery(ToDo.FIND_BY_DATE)
                .setParameter("p_data", new Date())
                .getResultList();
       return toJson(result);
    }

    public JsonArray find(Date d) {
        List<ToDo> result =  em.createNamedQuery(ToDo.FIND_BY_DATE)
                .setParameter("p_data", d)
                .getResultList();
        return toJson(result);
    }
    
    public ToDo find(long id) {
        return em.find(ToDo.class, id);
    }

    public JsonObject findJson(long id) {
        return toJson(em.find(ToDo.class, id));
    }

    public JsonArray find(String word) {
         List<ToDo> result = em.createNamedQuery(ToDo.FIND_BY_WORD, ToDo.class)
                .setParameter("word", "%" + word + "%")
                .getResultList();
          return toJson(result);
    }

    public void save(ToDo tosave) {
        tosave.setUtente(authUser);
        em.merge(tosave);
    }

    public void delete(long id) {
        ToDo finded = find(id);
        em.remove(finded);
    }

    private JsonArray toJson(List<ToDo> result){
        JsonArrayBuilder a = Json.createArrayBuilder();
        result.stream().map(this::toJson)
                .forEach(jsonTodo -> a.add(jsonTodo));
        return a.build();
    }
    
    private JsonObject toJson(ToDo v){
        return Json.createObjectBuilder()
                .add("id", v.getId())
                .add("titolo", v.getTitolo())
                .add("testo", v.getTesto())
                .add("il", DateUtils.dateToISO(v.getIl()))
                .add("user_id", v.getUtente().getId()).build();
    }
}
