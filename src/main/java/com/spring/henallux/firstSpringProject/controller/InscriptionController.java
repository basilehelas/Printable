package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.model.Constants;
import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.service.HobbiesService;
import com.spring.henallux.firstSpringProject.model.Hobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/inscription")
@SessionAttributes(Constants.CURRENT_USER)
public class InscriptionController {

    @ModelAttribute(Constants.CURRENT_USER)
    public User user() {
        return new User(); // 🔁 initialise l’objet stocké en session
    }
    private final HobbiesService hobbiesService;

    @Autowired // ⚠️ dit à Spring d'injecter le service automatiquement
    public InscriptionController(HobbiesService hobbiesService) {
        this.hobbiesService = hobbiesService;
    }

    // Méthode GET : affiche le formulaire
    @GetMapping
    public String showInscriptionForm(Model model) {
        model.addAttribute("userForm", new User()); // Objet vide pour data binding

        model.addAttribute("hobbies", hobbiesService.getHobbies());

        return "integrated:userInscription";
    }

    // Méthode POST : traite le formulaire
    @PostMapping("/submit")
    public String processInscription(
            @Valid @ModelAttribute(Constants.CURRENT_USER) User user,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("hobbies", hobbiesService.getHobbies());
            return "integrated:userInscription";
        }

        // 🔁 Redirection vers GiftController pour calculer le cadeau
        return "redirect:/gift";
    }
}
