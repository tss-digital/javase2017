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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author tss
 */
public class App2 {

    public static void main(String[] args) throws MalformedURLException, IOException {

        URL url = new URL("https://github.com/home.html");

        Document doc = Jsoup.parse(url,10000);
        
        Elements head = doc.select("head");
        
        System.out.println(head);
        
        
    }
}
