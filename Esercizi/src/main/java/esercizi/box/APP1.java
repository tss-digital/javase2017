/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizi.box;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tss
 */
public class APP1 {

    public static void main(String[] args) {

        Box b1 = new Box(10,20,30);
        Box b2 = new Box(10,20,30);
        b1.equals(b2);
        
        List<Box> scaffale = new ArrayList<>();

        scaffale.add(new Box(10, 10, 50));
        scaffale.add(new Box(20, 10, 50));
        scaffale.add(new Box(10, 30, 50));
        scaffale.add(new Box(10, 10, 50));
        
        boolean result = scaffale.contains(new Box(10, 10, 50));
        
        System.out.println("Risultato: " + result );
    }

}
