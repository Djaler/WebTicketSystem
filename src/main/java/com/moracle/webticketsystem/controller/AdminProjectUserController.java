package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.service.ProjectAccessService;
import com.moracle.webticketsystem.model.service.ProjectService;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by dmitry on 8/7/2016.
 */
@Controller
public class AdminProjectUserController {

    private final ProjectAccessService projectAccessService;
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public AdminProjectUserController(ProjectAccessService projectAccessService, ProjectService projectService,
                                      UserService userService) {
        this.projectAccessService = projectAccessService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/projects", method = RequestMethod.GET)
    public String index() {
        return "redirect:/admin/project/" + projectService.getFirst().getId();
    }

    @RequestMapping(value = "/admin/project/{id}", method = RequestMethod.GET)
    public String usersByProject(@PathVariable int id, Model model) {
        List<Project> projects = projectService.getAll();
        Project selectedProject = projects
                .stream()
                .filter(project -> project.getId() == id)
                .findFirst().get();

        List<User> usersInProject = projectAccessService.getUsersByProjectId(id);
        List<User> otherUsers = userService.getUsersNotInList(usersInProject);

        model.addAttribute("projects", projects);
        model.addAttribute("selectedProject", selectedProject);
        model.addAttribute("usersInProject", usersInProject);
        model.addAttribute("otherUsers", otherUsers);

        return "adminProjectUser";
    }
}
