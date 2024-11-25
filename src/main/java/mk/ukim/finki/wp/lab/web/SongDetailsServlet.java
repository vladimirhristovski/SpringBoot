//package mk.ukim.finki.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Artist;
//import mk.ukim.finki.wp.lab.model.Song;
//import mk.ukim.finki.wp.lab.service.ArtistService;
//import mk.ukim.finki.wp.lab.service.SongService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet(name = "song-details-servlet", urlPatterns = "/servlet/songDetails")
//public class SongDetailsServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final ArtistService artistService;
//    private final SongService songService;
//
//    public SongDetailsServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
//
//        this.springTemplateEngine = springTemplateEngine;
//        this.artistService = artistService;
//        this.songService = songService;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//
//        String trackId = req.getParameter("trackId");
//        String artistId = req.getParameter("artistId");
//
//
//        Optional<Artist> artist = artistService.ArtistfindById(Long.parseLong(artistId));
//        Optional<Song> song = songService.findByTrackId(Long.parseLong(trackId));
//        if (song.isPresent() && artist.isPresent()) {
//            songService.addArtistToSong(artist.get(), song.get());
//            context.setVariable("song", song.get());
//        }
//
//        this.springTemplateEngine.process("songDetails.html", context, resp.getWriter());
//    }
//}
