/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esempioarray;

/**
 *
 * @author tss
 */
public class BolletteH2O {

    
}

class Bolletta {

    public Bolletta(int metriCubi, double importo) {
        this.metriCubi = metriCubi;
        this.importo = importo;
    }

    int metriCubi;
    double importo;

}

class Inquilino {

    String nome;

    int ultimaLettura;

    int letturaAttuale;

    public Inquilino(String nome) {
        this.nome = nome;
    }
}
