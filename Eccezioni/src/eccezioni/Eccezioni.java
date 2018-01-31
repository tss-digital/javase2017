/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezioni;

import javax.swing.JOptionPane;

/**
 *
 * @author tss
 */
public class Eccezioni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Start");
            metodo1();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " Mi spiace ma il programma sarà terminato");
        } finally {
            System.out.println("end");
        }
    }

    public static void metodo1() throws SommaException {
        Funzioni f = new Funzioni();
        f.sommaNumeriPositivi(-10, 10);
    }

}
