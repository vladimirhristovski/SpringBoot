package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songsRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songsRepository, AlbumRepository albumRepository) {
        this.songsRepository = songsRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songsRepository.findAllSongs();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        songsRepository.addArtistToSong(artist, song);
        return artist;
    }

    @Override
    public Optional<Song> findByTrackId(Long trackId) {
        return songsRepository.findByTrackId(trackId);
    }

    @Override
    public Optional<Song> save(String title, String genre, int releaseYear, Long albumId, Optional<Long> songId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        return songsRepository.save(title, genre, releaseYear, album, songId);
    }

    @Override
    public void deleteById(Long id) {
        songsRepository.deleteById(id);
    }
}
