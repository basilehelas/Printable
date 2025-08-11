package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping(value = "/authenticated")
public class AuthenticatedController {

    @RequestMapping(method = RequestMethod.GET)
    public String authenticated(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("Authenticated user: " + username);
        return "integrated:authenticated";
    }
}
