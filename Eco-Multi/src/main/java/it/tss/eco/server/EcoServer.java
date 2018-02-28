/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.eco.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tss
 */
public class EcoServer {

    private static final String END = "FINE";
    private static final int SERVER_PORT = 22000;
    private static final int THREAD_POOL = 5;

    public static void main(String[] args) throws IOException {
        System.out.println("Server Started");
        ServerSocket srvSock = new ServerSocket(SERVER_PORT);
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_POOL);

        while (true) {
            System.out.println("waiting connection....");
            final Socket s = srvSock.accept();
            System.out.println("connection accepted....");
            exec.execute(clientWorker(s));
        }
    }

    private static String echo(String line) {
        return line + " ... " + line + " ... " + line;
    }

    static Runnable clientWorker(Socket s) {
        return () -> {
            try {
                BufferedReader br;
                PrintWriter bw;
                String line;
                do {
                    System.out.println(String.format("waiting line from %s ....",
                            s.getRemoteSocketAddress()));
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    bw = new PrintWriter(s.getOutputStream(), true);
                    line = br.readLine();
                    System.out.println(String.format("line received %s", line));
                    bw.println(echo(line));
                } while (line == null || !END.equals(line.toUpperCase()));
                System.out.println("connection closed....");
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(EcoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    s.close();
                } catch (IOException ex) {
                    //no log
                }
            }
        };
    }
;
}
