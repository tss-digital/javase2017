/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.generics;

/**
 *
 * @author tss
 */
public class App1 {

    public static void main(String[] args) {
        long risultato = App1.<Integer>somma(10,20);
        
    }
    
    
    private static <T extends Number> long somma(T op1, T op2) {
        return  op1.longValue() + op2.longValue();
    }
}
