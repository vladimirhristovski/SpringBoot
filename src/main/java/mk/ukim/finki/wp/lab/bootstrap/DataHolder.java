package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumerations.Role;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = null;
    public static List<Song> songs = null;
    public static List<Album> albums = null;
    public static List<User> users = null;

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DataHolder(ArtistRepository artistRepository, SongRepository songRepository, AlbumRepository albumRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        artists = new ArrayList<>();
        if (this.artistRepository.count() == 0) {
            artists.add(new Artist("Bon", "Jovi", "Biography for Bon Jovi"));
            artists.add(new Artist("Tose", "Proeski", "Biography for Tose Proeski"));
            artists.add(new Artist("Freddie", "Mercury", "Biography for Freddie Mercury"));
            artists.add(new Artist("Djordje", "Balasevic", "Biography for Djordje Balasevic"));
            artists.add(new Artist("Marshall", "Mathers", "Biography for Marshall Mathers"));
            this.artistRepository.saveAll(artists);
        }

        Album a1 = new Album("New Jersey", "Rock", "1988");
        Album a2 = new Album("Igri bez granici", "Pop", "2007");
        Album a3 = new Album("Jazz", "Rock", "1978");
        Album a4 = new Album("Tri posleratna druga", "Folk Rock", "1989");
        Album a5 = new Album("Encore", "Rap", "2004");

        albums = new ArrayList<>();
        if (this.albumRepository.count() == 0) {
            albums.add(a1);
            albums.add(a2);
            albums.add(a3);
            albums.add(a4);
            albums.add(a5);
            this.albumRepository.saveAll(albums);
        }

        songs = new ArrayList<>();
        if (this.songRepository.count() == 0) {
            songs.add(new Song("Bad Medicine", "Rock", 1988, a1));
            songs.add(new Song("Mesecina", "Pop", 2007, a2));
            songs.add(new Song("Don't Stop Me Now", "Rock", 1979, a3));
            songs.add(new Song("Devojka sa Cardas nogama", "Folk Rock", 1989, a4));
            songs.add(new Song("Mockingbird", "Rap", 2005, a5));
            this.songRepository.saveAll(songs);
        }

        users = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("vladimir.hristovski", passwordEncoder.encode("vh"), "Vladimir", "Hristovski", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }

    }
}
