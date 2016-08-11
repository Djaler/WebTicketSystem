package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.service.ProjectAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by dmitry on 8/7/2016.
 */
@Controller
public class AdminProjectUserController {

    private final ProjectAccessService projectAccessService;

    @Autowired
    public AdminProjectUserController(ProjectAccessService projectAccessService) {
        this.projectAccessService = projectAccessService;
    }

    @RequestMapping(value = "/admin/projects", method = RequestMethod.GET)
    public String addUserToProject() {
        List<User> list = projectAccessService.getUsersByProjectId(1);

        return "adminProjectUser";
    }
}
