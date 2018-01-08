/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizi.playlist;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tss
 */
public class Playlist {

    private final List<Song> elenco;

    public Playlist() {
        elenco = new ArrayList<>();
    }

    public void add(Song song) {
        elenco.add(song);
    }

    public void remove(Song song) {
        elenco.removeIf(s -> s.equals(song));
    }

    public long durata() {
        long result = 0;
        for (Song song : elenco) {
            result += song.getDurata();
        }
        return result;
    }

    @Override
    public String toString() {
        return elenco.toString();
    }

}
