/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author tss
 */
public class App {

    public static void main(String[] args) throws MalformedURLException, IOException {

        URL url = new URL("https://github.com/home.html");

        URLConnection cn = url.openConnection();

        cn.connect();

        //InputStream in = cn.getInputStream();
        //InputStreamReader ir = new InputStreamReader(in);
        //BufferedReader br = new BufferedReader(ir);
        InputStreamReader br = new InputStreamReader(cn.getInputStream());
        
        int line;
        do {
            line = br.read();
            System.out.println((char)line);
        } while (line != -1);
        br.close();
    }
}
