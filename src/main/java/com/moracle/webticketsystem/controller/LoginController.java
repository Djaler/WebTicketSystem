package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/tickets";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String pass, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/tickets";
        }
        user = userService.login(login, pass);
        if(user == null){
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/tickets";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/";
    }
}
