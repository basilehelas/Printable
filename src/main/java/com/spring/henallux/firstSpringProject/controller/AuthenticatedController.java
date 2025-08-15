package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.model.User;
import com.spring.henallux.firstSpringProject.model.UserUpdate;
import com.spring.henallux.firstSpringProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/authenticated")
public class AuthenticatedController {

    private final UserService userService;

    public AuthenticatedController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String authenticated(Model model, Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        UserUpdate form = new UserUpdate();
        form.setEmail(userDetails.getEmail());
        form.setUsername(userDetails.getUsername());
        form.setAddress(userDetails.getAddress());
        form.setPhoneNumber(userDetails.getPhoneNumber());
        model.addAttribute("userUpdate", form);
        return "integrated:authenticated";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String modifyUser(@Valid @ModelAttribute("userUpdate") UserUpdate form,
                             BindingResult br,
                             Authentication authentication) {

        if (form.getPassword() != null && !form.getPassword().isBlank()) {
            if (form.getConfirmPassword() == null || !form.getPassword().equals(form.getConfirmPassword())) {
                br.rejectValue("confirmPassword", "password.mismatch", "Les mots de passe ne correspondent pas");
            }
        }

        if (br.hasErrors()) {
            return "integrated:authenticated";
        }

        String email = ((User) authentication.getPrincipal()).getEmail();

        userService.updateUser(
                email,
                form.getUsername(),
                form.getAddress(),
                form.getPhoneNumber(),
                form.getPassword()
        );

        return "redirect:/home";
    }
}
