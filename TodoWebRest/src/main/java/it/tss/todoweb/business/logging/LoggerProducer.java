/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.logging;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author tss
 */
public class LoggerProducer {

    @Produces
    public Logger getLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getClass().getCanonicalName());
    }
}
