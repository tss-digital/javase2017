/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.presentation;

import it.tss.todoweb.business.ToDo;
import it.tss.todoweb.business.ToDoStore;
import java.util.List;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author tss
 */
@Path("todos")
@Controller
public class AppController {
    @Inject
    private Models models;
    
    @Inject
    ToDoStore store;
    
    @GET
    public String all(){
        System.out.println("all GET...");
        models.put("all", store.findAll());
        return "elenco.jsp";
    }
}
