//package mk.ukim.finki.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.service.ArtistService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "artist-list-servlet", urlPatterns = "/servlet/artist")
//public class ArtistServlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    private final ArtistService artistService;
//
//    public ArtistServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.artistService = artistService;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
//        WebContext context = new WebContext(webExchange);
//
//        context.setVariable("trackId", req.getParameter("trackId"));
//        context.setVariable("artists", this.artistService.listArtists());
//
//        this.springTemplateEngine.process("artistsList.html", context, resp.getWriter());
//    }
//}
