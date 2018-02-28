/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tss
 */
public class App2 {

    public static void main(String[] args) {
        Contatore c = new Contatore();
        new Conta("wk1", c).start();
        new Conta("wk2", c).start();
    }
}

class Conta extends Thread {

    private final Contatore contatore;

    public Conta(String name, Contatore c) {
        super(name);
        this.contatore = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            contatore.incrementa();
        }

    }

}

class Contatore {

    private int valore;

    public synchronized void incrementa() {
        valore += 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Contatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(valore);
    }

    public int getValore() {
        return valore;
    }

}
