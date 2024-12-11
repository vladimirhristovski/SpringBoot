package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();

//    @PostConstruct
//    public void init() {
//        artists.add(new Artist(1L, "Bon", "Jovi", "Biography for Bon Jovi"));
//        artists.add(new Artist(2L, "Tose", "Proeski", "Biography for Tose Proeski"));
//        artists.add(new Artist(3L, "Freddie", "Mercury", "Biography for Freddie Mercury"));
//        artists.add(new Artist(4L, "Djordje", "Balasevic", "Biography for Djordje Balasevic"));
//        artists.add(new Artist(5L, "Marshall", "Mathers", "Biography for Marshall Mathers"));
//
//        Album a1 = new Album("New Jersey", "Rock", "1988");
//        Album a2 = new Album("Igri bez granici", "Pop", "2007");
//        Album a3 = new Album("Jazz", "Rock", "1978");
//        Album a4 = new Album("Tri posleratna druga", "Folk Rock", "1989");
//        Album a5 = new Album("Encore", "Rap", "2004");
//
//        songs.add(new Song("Bad Medicine", "Rock", 1988, a1));
//        songs.add(new Song("Mesecina", "Pop", 2007, a2));
//        songs.add(new Song("Don't Stop Me Now", "Rock", 1979, a3));
//        songs.add(new Song("Devojka sa Cardas nogama", "Folk Rock", 1989, a4));
//        songs.add(new Song("Mockingbird", "Rap", 2005, a5));
//
//        albums.add(a1);
//        albums.add(a2);
//        albums.add(a3);
//        albums.add(a4);
//        albums.add(a5);
//    }
}
