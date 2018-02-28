/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.threads;

/**
 *
 * @author tss
 */
public class App1 {

    public static void main(String[] args) {
        System.out.println("start program");

        Worker wk1 = new Worker("wk1");
        wk1.setPriority(Thread.MAX_PRIORITY);
        wk1.start();
        System.out.println("wk1 started");

        new Worker("wk2").start();
        System.out.println("wk2 started");

        new Thread(new Prova("wk3")).start();
        System.out.println("wk3 started");

        System.out.println("end program");
    }
}

class Worker extends Thread {

    public Worker(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.println(String.format("Contatore - %s - %s", getName(), i));
        }
        System.out.println(String.format("------------------------------------------- %s terminato", getName()));
    }

}

class Prova implements Runnable {

    private String nome;

    public Prova(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100000; i++) {
            System.out.println(String.format("Prova - %s - %s", this.nome, i));
        }
    }

}
