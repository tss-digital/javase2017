/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esempi;

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
public class BibliotecaManuale {

    public static void main(String[] args) throws IOException {

        Libro[] libreria = new Libro[3];

        int indice = 0;
        while (indice < libreria.length) {
            Libro libro = inserisciLibro();
            libreria[indice] = libro;
            indice++;
            System.out.println("Grazie, il libro è stato inserito in Libreria");
        }

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
