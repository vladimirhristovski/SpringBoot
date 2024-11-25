package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/details")
public class SongDetailsController {

    private final ArtistService artistService;
    private final SongService songService;

    public SongDetailsController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @PostMapping
    public String GetSongDetails(@RequestParam Long trackId,
                                 @RequestParam Long artistId,
                                 Model model) {
        Optional<Song> song = songService.findByTrackId(trackId);
        Optional<Artist> artist = artistService.ArtistfindById(artistId);
        if (song.isPresent() && artist.isPresent()) {
            songService.addArtistToSong(artist.get(), song.get());
            model.addAttribute("song", song.get());
        }
        return "songDetails";
    }

}
