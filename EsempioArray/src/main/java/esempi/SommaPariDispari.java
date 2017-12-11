/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esempi;

/**
 *
 * @author tss
 */
public class SommaPariDispari {

    public static void main(String[] args) {
        int[] numeri = {10, 5, 5, 10};

        int pari = 0;
        int dispari = 0;

        int indice = 0;

        while (indice < numeri.length) {
            if (indice % 2 == 0) {
                pari += numeri[indice];
            } else {
                dispari += numeri[indice];
            }
            indice++;
        }

        if (pari == dispari) {
            System.out.println("Pari uguali a dispari");
        } else {
            System.out.println("Pari diversi da dispari");
        }
    }
}
