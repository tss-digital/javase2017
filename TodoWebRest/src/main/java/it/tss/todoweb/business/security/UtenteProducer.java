/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.security;

import it.tss.todoweb.business.utenti.Utente;
import it.tss.todoweb.business.utenti.UtenteStore;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author tss
 */
@RequestScoped
public class UtenteProducer {
    
    @Inject
    UtenteStore store;
    
    Utente logged;
    
    public void onAuthenticateUser(@Observes @AuthSuccess String usr){
        Optional<Utente> u = store.findByUser(usr);
        logged = u.get();
    }
    
    @Produces
    @AuthUser
    @RequestScoped
    public Utente getLogged(){
        return logged;
    }
}
