/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizi.box;

/**
 *
 * @author tss
 */
public class APP {

    public static void main(String[] args) {
        Box grande = new Box(20, 30, 40);
        Box grande2 = new Box(30, 20, 40);
        Box piccolo = new Box(10, 10, 50);
        System.out.println("Grande: " + grande.toString());
        System.out.println("Grande2: " + grande2.toString());
        System.out.println("Piccolo: " + piccolo.toString());
        System.out.println("grande == piccolo? " + grande.equals(grande2));
        System.out.println("piccolo sta in grande? " + piccolo.contenibileIn(grande));
        
        System.out.println(grande2.getClass().getSimpleName());
    }
}
