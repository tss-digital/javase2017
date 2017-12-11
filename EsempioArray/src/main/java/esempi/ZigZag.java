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
public class ZigZag {

    public static void main(String[] args) {

        int[] numeri = {54, 3, 6, 9, 2, 1, 35, 98, 10, 27};

        int su = 0;
        int giu = numeri.length - 1;

        while (su <= giu) {
            System.out.println(numeri[su]);
            if (su != giu) {
                System.out.println(numeri[giu]);
            }
            su++;
            giu--;
        }
    }
}
