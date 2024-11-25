package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Song> songs = songService.listSongs();
        model.addAttribute("songs", songs);
        return "listSongs";
    }

    @GetMapping("/add-song")
    public String addProductPage(Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId,
                           @RequestParam Optional<Long> songId) {
        songService.save(title, genre, releaseYear, albumId, songId);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId, Model model) {
        if (songService.findByTrackId(songId).isPresent()) {
            Song song = songService.findByTrackId(songId).get();
            List<Album> albums = albumService.findAll();
            model.addAttribute("albums", albums);
            model.addAttribute("song", song);
            return "add-song";
        }
        return "redirect:/songs?error=Song not found";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteById(id);
        return "redirect:/songs";
    }

}
