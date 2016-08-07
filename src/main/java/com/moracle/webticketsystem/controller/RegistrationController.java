package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.Role;
import com.moracle.webticketsystem.model.enums.RoleEnum;
import com.moracle.webticketsystem.model.exception.UserAlreadyExists;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String doRegistration(@RequestParam String login, @RequestParam String pass,
                                 @RequestParam String name, Model model) {
        try {
            userService.registrationNewUser(login, pass,
                    name, new Role(RoleEnum.USER.toID(), RoleEnum.USER));
        } catch (UserAlreadyExists ex) {
            model.addAttribute("userExistsError", true);
            return "registration";
        }
        return "redirect:/";
    }
}
