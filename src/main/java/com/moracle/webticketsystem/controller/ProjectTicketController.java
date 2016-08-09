package com.moracle.webticketsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class ProjectTicketController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String tickets(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        /* ИЛИ можно вот так. Тогда Principal не нужен
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken) == true) {
            return "redirect:/login";
        }
        */
        return "projectTicket";
    }

    @RequestMapping(value = "/createticket", method = RequestMethod.GET)
    public String createTicket() {
        return "createTicket";
    }
}
