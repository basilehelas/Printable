package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.model.MagicKeyForm;
import com.spring.henallux.firstSpringProject.dataAccess.dao.MagicKeyDataAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value="/hello")
public class WelcomeController {

    private final MagicKeyDataAccess magicKeyDAO; // ⚠️ interface, pas l’implémentation

    // Injection par constructeur (Spring détecte tout seul)
    public WelcomeController(MagicKeyDataAccess magicKeyDAO) {
        this.magicKeyDAO = magicKeyDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("message", "Hello, welcome to our Spring application!");
        model.addAttribute("pageTitle", "HOHOHO");
        model.addAttribute("magicKeyForm", new MagicKeyForm());
        return "integrated:welcome";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitMagicKey(@ModelAttribute("magicKeyForm") MagicKeyForm magicKeyForm, Model model) {
        String key = magicKeyForm.getMagicKey();
        if (key != null) key = key.trim();

        // Récupère les clés depuis la BD via le DAO
        ArrayList<String> keys = magicKeyDAO.getMagicKeys();

        if (keys.contains(key)) { // ✅ comparaison avec la liste en BD
            model.addAttribute("message", "Access granted!");
            model.addAttribute("pageTitle", "HOHOHO");
            return "integrated:welcome";
        } else {
            // ATTENTION : en redirect, les attributs du Model sont perdus.
            // Si tu veux afficher un message sur /inscription, utilise RedirectAttributes (voir plus bas).
            return "redirect:/inscription";
        }
    }
}
