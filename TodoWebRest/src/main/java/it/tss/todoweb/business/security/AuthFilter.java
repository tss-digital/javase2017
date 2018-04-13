/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.security;

import it.tss.todoweb.business.utenti.Utente;
import it.tss.todoweb.business.utenti.UtenteStore;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author tss
 */
@AuthRequired
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private static final String REALM = "todos";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Inject
    UtenteStore utenteStore;
    
    @Override
    public void filter(ContainerRequestContext rc) throws IOException {

        // Get the Authorization header from the request
        String authorizationHeader = rc.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(rc);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            // Validate the token
            Utente u = utenteStore.validateTokenRequest(token);
            
            settingSecurityContext(rc, u);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            abortWithUnauthorized(rc);
        }

    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private void settingSecurityContext(ContainerRequestContext rc, Utente u) {
        final SecurityContext currentSecurityContext = rc.getSecurityContext();
        rc.setSecurityContext(new SecurityContext() {

            
            @Override
            public Principal getUserPrincipal() {
                return () -> u.getUsername();
            }

            @Override
            public boolean isUserInRole(String role) {
                System.out.println("isUserInRole().." + role);
                return true;
            }

            @Override
            public boolean isSecure() {
                return currentSecurityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return AUTHENTICATION_SCHEME;
            }
        });
    }
}