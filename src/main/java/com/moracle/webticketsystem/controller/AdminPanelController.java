package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.service.ProjectAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dmitry on 8/7/2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminPanelController {

    private ProjectAccessService projectAccessService;

    @Autowired
    public AdminPanelController(ProjectAccessService projectAccessService) {
        this.projectAccessService = projectAccessService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String admin(){
        return "adminPanel";
    }

    @RequestMapping(value = "/addusertoproject", method = RequestMethod.GET)
    public String addUserToProject(){
        projectAccessService.addLink(5,5);
        return "adminPanel";
    }
}
