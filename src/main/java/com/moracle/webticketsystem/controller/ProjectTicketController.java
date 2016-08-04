package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class ProjectTicketController {

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String tickets(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("userLogin", user.getLogin());
        return "projectTicket";
    }
}
