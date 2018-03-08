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
        } while (cmd != 4);
    }

    private static void stampaMenu() {
        System.out.println("-------------- Menu --------------------------------");
        System.out.println("1 - Inserisci Nuovo ToDo");
        System.out.println("2 - Visualizza tutti i ToDo in archivio");
        System.out.println("3 - Visualizza tutti i ToDo di oggi");
        System.out.println("4 - Termina");
        System.out.println("-------------- Menu --------------------------------");
        System.out.println("");
    }

    private static void esegui(int cmd) throws ParseException, InterruptedException {
        switch(cmd){
            case 1:
                inserisci();
                break;
            case 2:
                visualizzaToDo();
                break;
            case 3:
                visualizzaToDo(new Date());
                break;
            case 4:
                termina();
                break;
        }
    }

    public static void save(ToDo todo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(todo);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private static void inserisci() throws ParseException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Titolo: ");
        Thread.sleep(100);
        String titolo = sc.nextLine();
        System.out.println("Testo: ");
        String testo = sc.nextLine();
        System.out.println("Il: ");
        String il = sc.nextLine();
        Date data = df.parse(il);
        save(new ToDo(titolo, testo, data));
    }

    private static void visualizzaToDo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void visualizzaToDo(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void termina() {
        System.out.println("Arrivederci...");
        System.exit(0);
    }

}
