/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezioni;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tss
 */
public class MainChecked {

    public static void main(String[] args) {

        try {
            Path path = Paths.get("/tmp/aa1.txt");
            List<String> righe = Files.readAllLines(path);
            System.err.println(righe.toString());
        } catch (IOException | RuntimeException ex) {
            System.out.println("Errore nell'apertura del file");
        } catch (Exception ex) {
            System.out.println("Errore nell'apertura del file");
        }finally{
            System.err.println("Fine programma");
        }
    }
}
