package com.moracle.webticketsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }

        /* ИЛИ можно вот так. Тогда Principal не нужен
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken) == false) {
            return "redirect:/";
        }
        */
        return "login";
    }
}
