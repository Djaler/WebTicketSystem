package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.service.RoleService;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by boggard on 12.08.2016.
 */
@Controller
public class AdminUsersController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminUsersController(UserService userService, RoleService roleService) {
        this.userService=userService;
        this.roleService=roleService;
    }

    @RequestMapping(value = "/admin/roles", method = RequestMethod.GET)
    public String index(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users",users);
        return "adminUsers";
    }

    @RequestMapping(value = "/admin/roles", method = RequestMethod.POST)
    public void changeRole(@RequestParam(value = "login") String login, @RequestParam(value = "role") String role) {
        User user = userService.getByLogin(login);
        user.setRole(roleService.getByRole(role));
        userService.update(user);
    }

}
