/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezioni;

/**
 *
 * @author tss
 */
public class MainStackOverflow {

    public static void main(String[] args) {
        try {
            metodo1();
        } catch (StackOverflowError ex) {
            System.err.println("Qualcosa è andato storto");
            System.err.println(ex.getMessage());
        }
    }

    public static void metodo1() {
        metodo1();
    }
}
