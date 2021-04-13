package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy")
public class Policy {

    @GetMapping("/privacy")
    private String privacy(){
        return "privacy-policy";
    }

    @GetMapping("/content")
    private String content(){
        return "content-policy";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){
        return "about-us";
    }
}
