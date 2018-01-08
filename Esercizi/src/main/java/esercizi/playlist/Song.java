/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizi.playlist;

import java.util.Objects;

/**
 *
 * @author tss
 */
public class Song {
    private final String nome;
    private final int durata;

    public Song(String nome, int durata) {
        this.nome = nome;
        this.durata = durata;
    }

    public String getNome() {
        return nome;
    }

    public int getDurata() {
        return durata;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nome);
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
        final Song other = (Song) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Song{" + "nome=" + nome + ", durata=" + durata + '}';
    }
    
    
}
