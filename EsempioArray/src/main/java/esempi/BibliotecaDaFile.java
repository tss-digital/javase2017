/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esempi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tss
 */
public class BibliotecaDaFile {

    public static void main(String[] args) throws IOException, URISyntaxException {

        List<Libro> libreria = new ArrayList<>();

        //Legge un file di testo e ritorna un ArrayList di righe
        List<String> righe = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("./files/libri.txt").toURI()));

        System.out.println("------------start importazione libri da file----------------");
        //Scorro arraylist di righe e per ogni riga aggiungo un libro alla libreria  
        for (String riga : righe) {
            libreria.add(creaLibro(riga));
        }

        
        System.out.println("------------end importazione----------------");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Cerca titoli???");
        String ricerca = scanner.nextLine();

        List<Libro> risultati = cercaInLibreria(libreria, ricerca);

        System.out.println(String.format("Trovati %s libri corrispondenti", risultati.size()));

        System.out.println(risultati);

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

