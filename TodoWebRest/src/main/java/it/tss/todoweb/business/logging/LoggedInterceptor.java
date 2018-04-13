/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.logging;

import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author tss
 */
@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggedInterceptor implements Serializable {
    
    @Inject
    Logger log;
    
    @AroundInvoke
    public Object logMethodCall(InvocationContext ic) throws Exception{
        
        log.info("----- Chiamata al metodo "
                + ic.getMethod().getName() + " nella classe " 
                + ic.getMethod().getDeclaringClass().getCanonicalName() );
        return ic.proceed();
    }
}
