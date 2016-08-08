package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.UserInfo;
import com.moracle.webticketsystem.model.entity.Role;
import com.moracle.webticketsystem.model.enums.RoleEnum;
import com.moracle.webticketsystem.model.exception.UserAlreadyExists;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }

        /* ИЛИ можно вот так. Тогда Principal не нужен
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken) == false) {
            return "redirect:/";
        }
        */
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String doRegistration(@ModelAttribute("userInfo") UserInfo userInfo, Model model) {
        try {
            userService.registrationNewUser(userInfo.getLogin(), userInfo.getPassword(),
                    userInfo.getName(), new Role(RoleEnum.USER.toID(), RoleEnum.USER));
        } catch (UserAlreadyExists ex) {
            model.addAttribute("userExistsError", true);
            return "registration";
        }
        return "redirect:/";
    }
}
