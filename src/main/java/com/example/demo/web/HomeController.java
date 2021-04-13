package com.example.demo.web;

import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ModelAttribute("allMovies")
    public List<MovieEntity> createAllMovies() {
        return  movieService.findAll();
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("actionMovies", movieService.findAllMoviesByGenre(Genre.ACTION));
        model.addAttribute("fantasyMovies", movieService.findAllMoviesByGenre(Genre.FANTASY));
        model.addAttribute("thrillerMovies", movieService.findAllMoviesByGenre(Genre.THRILLER));
        model.addAttribute("comedyMovies", movieService.findAllMoviesByGenre(Genre.COMEDY));

        return "home";
    }

    @GetMapping("/genre/action")
    public String action(Model model){

        model.addAttribute("actionMovies", movieService.findAllMoviesByGenre(Genre.ACTION));
        model.addAttribute("fantasyMovies", movieService.findAllMoviesByGenre(Genre.FANTASY));
        model.addAttribute("thrillerMovies", movieService.findAllMoviesByGenre(Genre.THRILLER));
        model.addAttribute("comedyMovies", movieService.findAllMoviesByGenre(Genre.COMEDY));
        return "genre-action";
    }

    @GetMapping("/genre/fantasy")
    public String fantasy(Model model){

        model.addAttribute("actionMovies", movieService.findAllMoviesByGenre(Genre.ACTION));
        model.addAttribute("fantasyMovies", movieService.findAllMoviesByGenre(Genre.FANTASY));
        model.addAttribute("thrillerMovies", movieService.findAllMoviesByGenre(Genre.THRILLER));
        model.addAttribute("comedyMovies", movieService.findAllMoviesByGenre(Genre.COMEDY));
        return "genre-fantasy";
    }
}
