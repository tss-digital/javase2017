/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.logging;

import javax.enterprise.event.Observes;
import it.tss.todoweb.business.security.AuthSuccess;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author tss
 */
public class Trace {
    @Inject
    Logger log;
    
    public void onAuthenticateUser(@Observes @AuthSuccess String usr){
        log.log(Level.WARNING, "onAuthenticateUser()...");
    }
}
