package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.TicketInfo;
import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.enums.PriorityEnum;
import com.moracle.webticketsystem.model.service.ProjectService;
import com.moracle.webticketsystem.model.service.TicketService;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class ProjectTicketController {


    private final TicketService ticketService;
    private final ProjectService projectService;
    private final UserService userService;
    private final int ticketsOnPage=15;

    @Autowired
    public ProjectTicketController(TicketService ticketService, ProjectService projectService, UserService userService) {
        this.ticketService = ticketService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        Project project = projectService.getFirst();
        return "redirect:/project/" + project.getId() + "/page1";
    }

    @RequestMapping(value = "/project/{id}/page{currentpage}", method = RequestMethod.GET)
    public String tickets(@PathVariable int id, @PathVariable int currentpage, Model model) {
        currentpage--;
        List<Project> projects = projectService.getAll();
        Project selectedProject = projects
                .stream()
                .filter(project -> project.getId() == id)
                .findFirst().get();
        List<Ticket> tickets = ticketService.getByProject(projectService.getById(id), currentpage, ticketsOnPage);

        model.addAttribute("projects", projects);
        model.addAttribute("selectedProject", selectedProject);
        model.addAttribute("tickets",tickets);
        model.addAttribute("currentpage", currentpage);
        return "projectTicket";
    }

    @RequestMapping(value = "/project/{id}/page{currentpage}/createticket", method = RequestMethod.POST)
    public String doCreateTicket(@PathVariable int id, @PathVariable int currentpage,
                                 @ModelAttribute TicketInfo ticketInfo, Principal principal) {
        Project project = projectService.getById(id);
        User user = userService.getByLogin(principal.getName());
        Ticket newTicket = new Ticket(user, project, ticketInfo.getSubject(), ticketInfo.getDescription(),
                PriorityEnum.toEnum(ticketInfo.getPriority()));
        ticketService.addTicket(newTicket);
        return "redirect: /project/" + id + "/page" + currentpage;
    }
}
