/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss;

/**
 *
 * @author tss
 */
public class A {

    protected String msg;

    public A(String msg) {
        this.msg = msg;
        System.out.println("costruttore di A..." + msg);
    }

    public void m1() {
        System.out.println("sono il metodo m1 della classe A");
    }

    public String getMsg() {
        return msg;
    }

}
