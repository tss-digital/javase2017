/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.employeefx;

import java.util.Objects;

/**
 *
 * @author tss
 */
public class Impiegato {

    private Integer numero;
    private String nome;
    private String cognome;
    private String email;
    private String ufficio;
    private String mansione;
    private String responsabile;

    public Impiegato() {
    }

    
    public Impiegato(Integer numero, String nome, String cognome, String email, String ufficio, String mansione, String reponsabile) {
        this.numero = numero;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.ufficio = ufficio;
        this.mansione = mansione;
        this.responsabile = reponsabile;
    }

    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUfficio() {
        return ufficio;
    }

    public void setUfficio(String ufficio) {
        this.ufficio = ufficio;
    }

    public String getMansione() {
        return mansione;
    }

    public void setMansione(String mansione) {
        this.mansione = mansione;
    }

    public String getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(String responsabile) {
        this.responsabile = responsabile;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.numero);
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
        final Impiegato other = (Impiegato) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome + " " + cognome ;
    }
    
    
}
