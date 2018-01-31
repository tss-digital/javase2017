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
public class Funzioni {

    public int sommaNumeriPositivi(int a, int b) throws SommaException {
        if (a < 0 || b < 0) {
            throw new SommaException(a, b);
        }
        return a + b;
    }
}
