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
public class SommaException extends Exception {

    private final int operando1;
    private final int operando2;

    public SommaException(int o1, int o2) {
        super(String.format("La somma tra %s e %s non può essere eseguita", o1, o2));
        this.operando1 = o1;
        this.operando2 = o2;
    }

    public int getOperando1() {
        return operando1;
    }

    public int getOperando2() {
        return operando2;
    }

    
}
