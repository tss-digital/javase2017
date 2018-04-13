/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.utenti;

import it.tss.todoweb.business.DateUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import it.tss.todoweb.business.security.AuthSuccess;
import it.tss.todoweb.business.security.AuthFailed;

/**
 *
 * @author tss
 */

@Stateless
public class UtenteStore implements Serializable {

    @Inject
    @AuthSuccess
    private Event<String> authSuccess;
    
    @Inject
    @AuthFailed
    private Event<String> authFailed;
    
    @PersistenceContext
    private EntityManager em;

    public Optional<Utente> find(Long id) {
        Utente find = em.find(Utente.class, id);
        return find == null ? Optional.empty() : Optional.of(find);
    }

    public Optional<Utente> findByUser(String username) {
        try {
            return Optional.of(
                    em.createNamedQuery(Utente.FIND_BY_USER, Utente.class)
                            .setParameter("usr", username)
                            .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<Utente> findByUserPwd(String username, String password) {
        try {
            return Optional.of(
                    em.createNamedQuery(Utente.FIND_BY_USER_PWD, Utente.class)
                            .setParameter("usr", username)
                            .setParameter("pwd", password)
                            .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<Utente> findByToken(String token) {
        try {
            return Optional.of(
                    em.createNamedQuery(Utente.FIND_BY_TOKEN, Utente.class)
                            .setParameter("token", token)
                            .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Utente save(Utente u) {
        return em.merge(u);
    }

    public Utente login(String username, String password) {
        
        Optional<Utente> find = findByUserPwd(username, password);
        if (find.isPresent()) {
            Utente logged = find.get();
            logged = updateToken(logged);
            authSuccess.fire(username);
            return logged;
        }
        authFailed.fire(username);
        throw new EJBException("Accesso negato");
    }

    private Utente updateToken(Utente u) {
        String token = UUID.randomUUID().toString();
        u.setToken(token);
        u.setTokenEnd(DateUtils.scadenzaToken(10));
        return save(u);
    }

    private boolean isValidToken(Utente u) {
        return new Date().compareTo(u.getTokenEnd()) <= 0;
    }

    public Utente validateTokenRequest(String token) {
        Optional<Utente> find = findByToken(token);

        Utente logged = find.orElseThrow(() -> {
            return new EJBException("Accesso negato, token inesistente");
        });

        if (!isValidToken(logged)) {
            throw new EJBException("Accesso negato, token scaduto");
        }

        authSuccess.fire(logged.getUsername());
        
        return logged;
    }
}
