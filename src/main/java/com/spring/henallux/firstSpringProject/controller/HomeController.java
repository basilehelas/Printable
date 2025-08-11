package com.spring.henallux.firstSpringProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Printable home page");
        return "integrated:home"; // Correspond au nom défini dans tiles.xml
    }
}
