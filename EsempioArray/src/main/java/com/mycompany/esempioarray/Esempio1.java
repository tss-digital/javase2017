/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esempioarray;

/**
 *
 * @author tss
 */
public class Esempio1 {

    /*
    public static void main(String[] args) throws InterruptedException {

        int[] numeri = {2, 6, 76, 23, 12};

        String[] lettere = {"G", "Z", "G", "Z", "K"};

        for (int indice = 0; indice < numeri.length; indice++) {
            System.out.println(numeri[indice]);
        }

        System.out.println("------------ while ---------------------");

        int indice = 0;
        while (indice < numeri.length) {
            System.out.println(numeri[indice]);
            System.out.println(lettere[indice]);
            indice++;
        }

        System.out.println("------------ metodo stampa pari ---------------------");

        indice = 0;
        while (indice < numeri.length) {
            stampaSeNecessario(numeri[indice]);
            indice++;
        }

        System.out.println("------------ stampa rallenty ---------------------");

        indice = 0;
        while (indice < numeri.length) {
            rallenta(2);
            System.out.println(numeri[indice]);
            indice++;
        }

    }

     */
    private static void stampaSeNecessario(int valore) {
        if (valore % 2 == 0) {
            System.out.println(valore);
        }
    }

    private static void rallenta(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

}
