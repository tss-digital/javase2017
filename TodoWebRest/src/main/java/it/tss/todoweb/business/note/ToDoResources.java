/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.note;

import it.tss.todoweb.business.DateUtils;
import it.tss.todoweb.business.security.AuthRequired;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@AuthRequired
@Path("todo")
@Stateless
public class ToDoResources {

    @Inject
    ToDoStore store;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(store.findAllJson()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        return Response.ok(store.findJson(id)).build() ;
    }

    @GET
    @Path("search/today")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        return Response.ok(store.find(new Date())).build() ;
    }

    @GET
    @Path("find/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("word") String word) {
        return Response.ok( store.find(word)).build() ;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("titolo") String titolo,
            @FormParam("testo") String testo, @FormParam("il") String il) {
        
        ToDo tosave = new ToDo(titolo, testo, DateUtils.dateFromString(il));
        store.save(tosave);
        return Response.ok().build();

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("id") Long id, @FormParam("titolo") String titolo,
            @FormParam("testo") String testo, @FormParam("il") String il) {
        ToDo tosave;
        tosave = store.find(id);
        tosave.setTitolo(titolo);
        tosave.setTesto(testo);
        tosave.setIl(DateUtils.dateFromString(il));
        store.save(tosave);
        return Response.ok().build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        store.delete(id);
        return Response.ok().build();
    }

}
