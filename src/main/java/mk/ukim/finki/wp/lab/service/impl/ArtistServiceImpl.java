package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistsRepository;

    public ArtistServiceImpl(ArtistRepository artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistsRepository.findAll();
    }

    @Override
    public Optional<Artist> ArtistfindById(Long id) {
        return artistsRepository.findById(id);
    }
}
