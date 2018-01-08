/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizi.playlist;

/**
 *
 * @author tss
 */
public class APP {

    public static void main(String[] args) {
     
        Playlist p = new Playlist();
        p.add(new Song("azzurro", 230));
        p.add(new Song("volare", 55));
        p.add(new Song("l'appuntamento", 55));
        p.add(new Song("volare", 55));
        
        System.out.println(p);
        
        p.remove(new Song("volare", 55));
        
        System.out.println(p);
        
    }
}
