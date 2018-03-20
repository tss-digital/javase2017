/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tss
 */
@Path("todo")
public class ToDoResources {

    @Inject
    ToDoStore store;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ToDo> all() {
        return store.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ToDo find(@PathParam("id") Long id){
        return store.find(id);
    }
    
    @GET
    @Path("search/today")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ToDo> find(){
        return store.findToday();
    }
    
    @GET
    @Path("find/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ToDo> find(@PathParam("word") String word){
        return store.find(word);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void create(@FormParam("titolo") String titolo,
            @FormParam("testo") String testo, @FormParam("il") String il ){
        ToDo tosave;
        try {
            tosave = new ToDo(titolo, testo, DateUtils.dateFromString(il));
            store.save(tosave);
        } catch (ParseException ex) {
            Logger.getLogger(ToDoResources.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
