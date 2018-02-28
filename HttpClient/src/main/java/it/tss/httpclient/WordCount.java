/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author tss
 */
public class WordCount {

    private static final String PAGE = "http://lastampa.it";
    private static final String[] TAGS = {"h1", "h2", "h3", "h4", "h5", "h6", "p", "span"};

    public static void main(String[] args) throws MalformedURLException, IOException {

        URL url = new URL(PAGE);

        Map<String, Integer> result = new HashMap<>();

        Document doc = Jsoup.parse(url, 10000);

        for (String tag : TAGS) {
            Elements elements = doc.select(tag);
            for (Element e : elements) {
                List<String> words = estraiParole(e.text());
                aggiornaConteggio(result, words);
            }
        }

        stampa(result);
    }

    private static List<String> estraiParole(String testo) {
        String normalizzata = testo.replaceAll("[^a-zA-Z]", " ");
        String[] split = normalizzata.split(" ");
        List<String> parole = new ArrayList<>();
        for (String p : split) {
            if (!p.trim().isEmpty() && p.trim().length() > 3) {
                parole.add(p.trim());
            }
        }
        return parole;
    }

    private static void aggiornaConteggio(Map<String, Integer> result, List<String> words) {
        for (String word : words) {
            if (result.containsKey(word)) {
                Integer count = result.get(word);
                result.put(word, count + 1);
            } else {
                result.put(word, 1);
            }
        }
    }

    private static void stampa(Map<String, Integer> result) {

        LinkedHashMap<String, Integer> sorted = result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println(String.format("%s %s %s",
                    entry.getKey(),
                    entry.getKey().length() > 6 ? "\t" : "\t\t",
                    entry.getValue()));
        }
    }
}
