/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business;

import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author tss
 */
@Provider
public class EjbExceptionMapper implements ExceptionMapper<EJBException> {

    @Inject
    Logger log;
    
    @Override
    public Response toResponse(EJBException ex) {
        log.info("EjbExceptionMapper..... error: " + ex.getMessage());
        Throwable cause = ex.getCause();
        Response unknowError = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("causa", "Errore sconosciuto...")
                .build();
        if (cause == null) {
            return unknowError;
        }

        if (cause instanceof IllegalArgumentException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("causa", "Parametri richiesta non corretti...")
                    .header("causa-msg", cause.getMessage())
                    .build();
        }
        
        return unknowError;
    }

}
