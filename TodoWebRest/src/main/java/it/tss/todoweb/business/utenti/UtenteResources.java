/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.utenti;

import java.math.BigDecimal;
import java.util.Optional;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@Path("/utenti")
public class UtenteResources {

    @Inject
    UtenteStore store;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Utente u) {
        try {
            Utente logged = store.login(u.getUsername(), u.getPassword());
            JsonObject json = Json.createObjectBuilder()
                    .add("token", logged.getToken())
                    .build();
            return Response.ok(json).build(); 
        } catch (EJBException ex) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
    }
}
