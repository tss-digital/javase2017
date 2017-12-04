/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettaacqua;

/**
 *
 * @author tss
 */
public class App {

    public static void main(String[] args) {

        int num = 3;
        double consumoTot = 200;
        double consumoTotEuro = 200;
        double costoMetro = consumoTot / consumoTotEuro;

        String[] nomi = {"A", "B", "C"};
        double[] consumoPrec = {35, 30, 40};
        double[] consummoSucc = {60, 70, 90};

        for (int inquilino = 0; inquilino < num; inquilino++) {
            System.out.println(String.format("--------------- %s --------------", nomi[inquilino]));
            System.out.println(String.format("Consumi: %s - %s = %s spesa: %s", 
                    consumoPrec[inquilino],
                    consummoSucc[inquilino], 
                    (consummoSucc[inquilino]- consumoPrec[inquilino]), 
                    (consummoSucc[inquilino]- consumoPrec[inquilino])*costoMetro));
            System.out.println("----------------------------------------------");
        }

    }
}
