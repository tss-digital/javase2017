/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import it.tss.A;

/**
 *
 * @author tss
 */
public class B extends A {
   
    public B(String msg) {
        super(msg);
        System.out.println("costruttore di B...");
    }

    public void m2() {
        System.out.println("sono il metodo m2 della classe B");
    }

    @Override
    public void m1() {
        super.m1();
        System.out.println("sono il metodo m1 riscritto dalla classe B");
    }
}
