/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.security;

import javax.enterprise.event.Observes;

/**
 *
 * @author tss
 */
public class Control {
    public void onAuthenticateUser(@Observes @AuthFailed String usr){
        System.out.println("onLogin failed.. " + usr);
    }
}
