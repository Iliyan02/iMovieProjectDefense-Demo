package com.example.demo.web;

import com.example.demo.model.binding.UserRegisterBindingModel;
import com.example.demo.model.service.UserServiceModel;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel createBindingModel(){
        return new UserRegisterBindingModel();
    }



    @GetMapping("/login")
//    @PreAuthorize("!isAuthenticated()")
    public String login(){
        return "login";
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerAndLogin(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (userService.userExists(userRegisterBindingModel.getUsername())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/users/register";
        }

        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        userService.registerAndLogin(userServiceModel);

        return "redirect:/home";

    }

    @GetMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                            String username) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("bad_credentials", true);
        modelAndView.addObject("username", username);

        modelAndView.setViewName("/login");

        return modelAndView;
    }
}
