/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoapp.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tss
 */
public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final ToDoStore store = new ToDoStore();

    public static void main(String[] args) {
        System.out.println("Start ToDo App .....");
        int cmd;
        do {
            stampaMenu();
            String line = sc.nextLine();
            cmd = Integer.parseInt(line);
            try {
                esegui(cmd);
            } catch (ParseException | InterruptedException ex) {
                System.out.println("Errore nell'esecuzione del comando.." + ex.getMessage());
            }
        } while (cmd != 6);
    }

    private static void stampaMenu() {
        System.out.println("-------------- Menu --------------------------------");
        System.out.println("1 - Inserisci Nuovo ToDo");
        System.out.println("2 - Visualizza tutti i ToDo in archivio");
        System.out.println("3 - Visualizza tutti i ToDo di oggi");
        System.out.println("4 - Elimina ToDo");
        System.out.println("5 - Aggiorna ToDo");
        System.out.println("6 - Termina");
        System.out.println("-------------- Menu --------------------------------");
        System.out.println("");
    }

    private static void esegui(int cmd) throws ParseException, InterruptedException {
        switch (cmd) {
            case 1:
                inserisci();
                break;
            case 2:
                visualizzaToDo();
                break;
            case 3:
                visualizzaToDoInData();
                break;
            case 4:
                elimina();
                break;
            case 5:
                aggiorna();
                break;
            case 6:
                termina();
                break;
        }
    }

    private static void inserisci() throws ParseException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Titolo: ");
        String titolo = sc.nextLine();
        System.out.println("Testo: ");
        String testo = sc.nextLine();
        System.out.println("Il: ");
        String il = sc.nextLine();
        Date data = df.parse(il);
        store.save(new ToDo(titolo, testo, data));
    }

    private static void aggiorna() throws ParseException, InterruptedException {
        System.out.println("Id ToDo da aggiornare: ");
        String key = sc.nextLine();
        long id = Long.parseLong(key);
        ToDo saved = store.find(id);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Titolo: ");
        System.out.print(saved.getTitolo());
        String titolo = sc.nextLine();
        System.out.println("Testo: ");
        System.out.print(saved.getTesto());
        String testo = sc.nextLine();
        System.out.println("Il: ");
        System.out.print(saved.getIl());
        String il = sc.nextLine();
        Date data = df.parse(il);
        
        saved.setTitolo(titolo);
        saved.setTesto(testo);
        saved.setIl(data);
        store.save(saved);
    }
    
    private static void visualizzaToDo() {
        store.findAll().stream().forEach(System.out::println);
    }

    private static void visualizzaToDoInData() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Data (dd/MM/yyyy): ");
            String data = sc.nextLine();
            Date d = df.parse(data);
            store.findByDate(d).stream().forEach(System.out::println);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void elimina() {
        System.out.println("Id ToDo da eliminare: ");
        String key = sc.nextLine();
        long id = Long.parseLong(key);
        store.delete(id);
    }

    private static void termina() {
        System.out.println("Arrivederci...");
        store.close();
        System.exit(0);
    }

}
