/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.eco.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author tss
 */
public class EcoClient {

    private static final String END = "FINE";
    private static final String SERVER_NAME = "192.168.40.212";
    private static final int SERVER_PORT = 22000;

    public static void main(String[] args) throws IOException {

        System.out.println("Client started..");
        Socket s = new Socket(SERVER_NAME, SERVER_PORT);
        System.out.println("Connected... " + s.getPort());
        
        PrintWriter bw = new PrintWriter(s.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Scanner scanner = new Scanner(System.in);
        String line;
        do {
            System.out.println("Scrivi una frase da mandare al server:");
            line = scanner.nextLine();
            bw.println(line);
            System.out.println(br.readLine());
        } while (!END.equals(line.toUpperCase()));
        System.out.println("Client ended..");
    }
}
