package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    public Optional<Song> findByTrackId(Long trackId);

    Optional<Song> save(String title, String genre, int releaseYear, Long albumId, Optional<Long> trackId);

    void deleteById(Long id);

    List<Song> findByAlbumId(Long albumId);

}

