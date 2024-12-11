package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {
    public List<Song> findAllSongs() {
        return DataHolder.songs;
    }

    public Optional<Song> findByTrackId(Long trackId) {
        return DataHolder.songs.stream().filter(song -> song.getId().equals(trackId)).findFirst();
    }

    public void addArtistToSong(Artist artist, Song song) {
        if (!song.getPerformers().contains(artist)) {
            song.getPerformers().add(artist);
        }
    }

    public Optional<Song> save(String title, String genre, int releaseYear, Album album, Optional<Long> trackId) {
        if (trackId.isPresent()) {
            Optional<Song> se = DataHolder.songs.stream().filter(s -> s.getId().equals(trackId.get())).findFirst();
            if (se.isPresent()) {
                Song s = se.get();
                s.setTitle(title);
                s.setGenre(genre);
                s.setReleaseYear(releaseYear);
                s.setAlbum(album);
            }
        } else {
            Song song = new Song(title, genre, releaseYear, album);
            DataHolder.songs.add(song);
            return Optional.of(song);
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        DataHolder.songs.removeIf(s -> s.getId().equals(id));
    }

}
