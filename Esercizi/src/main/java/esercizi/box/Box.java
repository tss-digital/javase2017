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
public class Box extends Object{

    private final int altezza, larghezza, profondita;

    public Box(int altezza, int larghezza, int profondita) {
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.profondita = profondita;
    }

    
    public boolean contenibileIn(Box box) {
        return this.altezza < box.getAltezza() && this.larghezza < box.getLarghezza() && this.profondita < box.getProfondita();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.altezza;
        hash = 97 * hash + this.larghezza;
        hash = 97 * hash + this.profondita;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Box other = (Box) obj;
        if (this.altezza != other.altezza) {
            return false;
        }
        if (this.larghezza != other.larghezza) {
            return false;
        }
        return this.profondita == other.profondita;
    }

    
    
    public boolean uguale(Box box) {
        return this.altezza == box.getAltezza() && this.larghezza == box.getLarghezza() == this.profondita < box.getProfondita();
    }

    public int getAltezza() {
        return altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public int getProfondita() {
        return profondita;
    }

    @Override
    public String toString() {
        return altezza + " X " + larghezza + " X " + profondita;
    }
    
    
}
