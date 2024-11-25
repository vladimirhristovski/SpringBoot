//package mk.ukim.finki.wp.lab.web.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.annotation.WebInitParam;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebFilter(filterName = "song-filter", urlPatterns = "/*",
//        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
//        initParams = {@WebInitParam(name = "ignore-path", value = "/songs")})
//public class SongFilter implements Filter {
//    private String ignorePath;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//        ignorePath = filterConfig.getInitParameter("ignore-path");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        String trackId = (String) req.getParameter("trackId");
//
//        String path = req.getServletPath();
//
//        if (!path.startsWith(ignorePath) && trackId == null) {
//            resp.sendRedirect("/songs");
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
