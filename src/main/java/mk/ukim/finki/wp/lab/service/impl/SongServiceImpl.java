package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
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
        return songsRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        if (!song.getPerformers().contains(artist)) {
            song.getPerformers().add(artist);
            songsRepository.save(song);
        }
        return artist;
    }

    @Override
    public Optional<Song> findByTrackId(Long trackId) {
        return songsRepository.findById(trackId);
    }

    @Override
    public Optional<Song> save(String title, String genre, int releaseYear, Long albumId, Optional<Long> songId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        if (songId.isPresent()) {
            Optional<Song> song = songsRepository.findById(songId.get());
            if (song.isPresent()) {
                song.get().setTitle(title);
                song.get().setGenre(genre);
                song.get().setReleaseYear(releaseYear);
                song.get().setAlbum(album);
                return Optional.of(songsRepository.save(song.get()));
            } else {
                return Optional.of(songsRepository.save(new Song(title, genre, releaseYear, album)));
            }
        } else {
            return Optional.of(songsRepository.save(new Song(title, genre, releaseYear, album)));
        }
    }

    @Override
    public void deleteById(Long id) {
        songsRepository.deleteById(id);
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        return songsRepository.findByAlbum_Id(albumId);
    }
}
