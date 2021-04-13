package com.example.demo.web;

import com.example.demo.service.LogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class StatsController {
    private final LogService logService;

    public StatsController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/statistics")
    public String stats(Model model) {
        model.addAttribute("logs", logService.findAllLogs());

        return "stats";
    }
}
