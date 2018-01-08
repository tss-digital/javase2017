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
public class DVD {

    protected final String disk;

    public DVD(String disk) {
        this.disk = disk;
    }

    public void read() {
        System.out.println("leggo il disco: " + disk);
    }
}
