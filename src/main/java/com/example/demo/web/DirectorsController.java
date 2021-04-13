package com.example.demo.web;

import com.example.demo.model.binding.DirectorAddBindingModel;
import com.example.demo.model.binding.MovieAddBindingModel;
import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.service.DirectorServiceModel;
import com.example.demo.service.DirectorService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/directors")
public class DirectorsController {
    private final DirectorService directorService;
    private final ModelMapper modelMapper;

    public DirectorsController(DirectorService directorService, ModelMapper modelMapper) {
        this.directorService = directorService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute("allDirectors")
    public List<DirectorEntity> createAllMovies() {
        return  directorService.findAll();
    }

    @ModelAttribute("directorAddBindingModel")
    public DirectorAddBindingModel createBindingModel() {
        return new DirectorAddBindingModel();
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String add(){
        return "directors-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid DirectorAddBindingModel directorAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("directorAddBindingModel",  directorAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.directorAddBindingModel", bindingResult);
            return "redirect:/directors/add";
        }

        if (directorService.containsDirector(directorAddBindingModel.getName())){
                redirectAttributes.addFlashAttribute("directorAddBindingModel", directorAddBindingModel);
                redirectAttributes.addFlashAttribute("exists", true);
                return "redirect:/directors/add";
        }



        DirectorServiceModel directorServiceModel = modelMapper.map
                (directorAddBindingModel, DirectorServiceModel.class);

        directorService.addDirector(directorServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteShow(){
        return "director-delete";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        directorService.deleteDirector(id);
        return "redirect:/directors/delete";
    }
}
