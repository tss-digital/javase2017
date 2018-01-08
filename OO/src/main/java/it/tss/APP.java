/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss;

import prova.B;

/**
 *
 * @author tss
 */
public class APP {

    public static void main(String[] args) {

        //prova(new A("xx"));
        prova(new B("xx"));
    }

    public static void prova(A a) {
        a.m1();
    }
}
