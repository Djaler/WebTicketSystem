package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.Role;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.enums.RoleEnum;
import com.moracle.webticketsystem.model.exception.UserAlreadyExists;
import com.moracle.webticketsystem.model.service.RoleService;
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
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/tickets";
        }
        return "registration";
    }

    @RequestMapping(value = "/doregistration", method = RequestMethod.POST)
    public String doRegistration(@RequestParam String login, @RequestParam String pass, @RequestParam String name,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/tickets";
        }
        try {
            userService.registrationNewUser(login, pass, name, new Role(RoleEnum.USER.toID(), RoleEnum.USER));
        } catch (UserAlreadyExists ex) {
            return "redirect:/registration";
        }
        session.setAttribute("user", userService.getByLogin(login));
        return "redirect:/tickets";
    }
}
