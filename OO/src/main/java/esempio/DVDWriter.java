/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esempio;

/**
 *
 * @author tss
 */
public class DVDWriter extends DVD {

    public DVDWriter(String disk) {
        super(disk);
    }

    @Override
    public void read() {
        System.out.println("leggo il disco a velocita 56X: " + disk);
    }

    public void write(String contenuto) {
        System.out.println("scrittura del contenuto su disco..");
    }
}
