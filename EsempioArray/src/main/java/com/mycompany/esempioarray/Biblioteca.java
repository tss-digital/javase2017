/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esempioarray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author tss
 */
public class Biblioteca {

    public static void main(String[] args) throws IOException {

        List<Libro> libreria = new ArrayList<>();

        /*
        inserimento libri manuale
        int indice = 0;
        while (indice < libreria.length) {
        Libro libro = inserisciLibro();
        libreria[indice] = libro;
        indice++;
        System.out.println("Grazie, il libro è stato inserito in Libreria");
        }
         */
        //Legge un file di testo e ritorna un ArrayList di righe
        List<String> righe = Files.readAllLines(Paths.get("/home/tss/Scrivania/libri.txt"));

        System.out.println("------------start importazione libri da file----------------");
        //Scorro arraylist di righe e per ogni riga aggiungo un libro alla libreria  
        for (int i = 0; i < righe.size(); i++) {
            String riga = righe.get(i);
            libreria.add(creaLibro(riga));
        }

        //ciclo for alternativo (foreach)
        /*
        for (String riga : righe) {
            libreria.add(creaLibro(riga));
        }
         */
        System.out.println("------------end importazione----------------");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Cerca titoli???");
        String ricerca = scanner.nextLine();

        List<Libro> risultati = cercaInLibreria(libreria, ricerca);

        System.out.println(String.format("Trovati %s libri corrispondenti", risultati.size()));

        System.out.println(risultati);

    }

    // Chiedo i dati del libro all'utente
    private static Libro inserisciLibro() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Titolo?");
        String titolo = scanner.nextLine();

        System.out.println("Autore?");
        String autore = scanner.nextLine();

        System.out.println("Prezzo?");
        double prezzo = scanner.nextDouble();

        System.out.println("ISBN?");
        String isbn = scanner.nextLine();

        return new Libro(titolo, autore, prezzo, isbn);
    }

    private static Libro creaLibro(String riga) {
        String[] campi = riga.split(",");
        return new Libro(campi[0], campi[1], Double.parseDouble(campi[2]), campi[3]);
    }

    private static List<Libro> cercaInLibreria(List<Libro> libreria, String ricerca) {
        
        List<Libro> result = new ArrayList<>();
        for (Libro libro : libreria) {
            if (libro.nome.contains(ricerca)) {
                result.add(libro);
            }
        }
        return result;
    }
}

class Libro {

    public Libro(String nome, String autore, double prezzo, String isbn) {
        this.nome = nome;
        this.autore = autore;
        this.prezzo = prezzo;
        this.isbn = isbn;
    }

    String nome;
    String autore;
    double prezzo;
    String isbn;
    String categoria;
    Editore editore;

    @Override
    public String toString() {
        return "Libro{" + "nome=" + nome + ", autore=" + autore + ", prezzo=" + prezzo + ", isbn=" + isbn + ", categoria=" + categoria + ", editore=" + editore + '}';
    }

}

class Editore {

    public Editore() {

    }

    String ragioneSociale;
    String indirizzo;
}
