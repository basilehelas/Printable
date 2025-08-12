package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "integrated:register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") User user, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "integrated:register";
        }

        // Vérification email déjà pris
        if (userService.emailExists(user.getEmail())) {
            errors.rejectValue("email", "email.exists", "Un compte existe déjà avec cet email.");
            return "integrated:register"; // reste sur la même page
        }

        userService.registerNewUser(user);
        return "redirect:/home";
    }
}
