package com.example.demo.web;

import com.example.demo.model.binding.MovieAddBindingModel;
import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.service.MovieServiceModel;
import com.example.demo.model.view.MovieViewModel;
import com.example.demo.service.DirectorService;
import com.example.demo.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final DirectorService directorService;
    private final ModelMapper modelMapper;
    private final MovieService movieService;

    public MovieController(DirectorService directorService, ModelMapper modelMapper, MovieService movieService) {
        this.directorService = directorService;
        this.modelMapper = modelMapper;
        this.movieService = movieService;
    }

    @ModelAttribute("movieAddBindingModel")
    public MovieAddBindingModel createBindingModel() {
        return new MovieAddBindingModel();
    }

    @ModelAttribute("allMovies")
    public List<MovieEntity> createAllMovies() {
        return  movieService.findAll();
    }



    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String add(Model model){

        model.addAttribute("directors", directorService.findAllDirectors());
        return "add-movie";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addConfirm(@Valid MovieAddBindingModel movieAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("movieAddBindingModel", movieAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.movieAddBindingModel", bindingResult);
            return "redirect:/movies/add";
        }

        if (movieService.containsMovie(movieAddBindingModel.getName())){
            redirectAttributes.addFlashAttribute("movieAddBindingModel", movieAddBindingModel);
            redirectAttributes.addFlashAttribute("exists", true);
            return "redirect:/movies/add";
        }


        MovieServiceModel movieServiceModel =
                modelMapper.map(movieAddBindingModel, MovieServiceModel.class);

        movieServiceModel.setUser(principal.getUsername());

//        movieServiceModel.setReleaseDate(movieAddBindingModel.
//                getReleaseDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        movieService.createMovie(movieServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/movie-page-details/{id}")
    public String details(@PathVariable Long id, Model model){
        MovieViewModel movieViewModel = movieService.findById(id);

        model.addAttribute("movie", movieViewModel);
        System.out.println("Passes from here");

        return "movie-page-details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        movieService.deleteMovie(id);
        return "redirect:/movies/delete";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteShow(){
        return "delete-movie";
    }



}
