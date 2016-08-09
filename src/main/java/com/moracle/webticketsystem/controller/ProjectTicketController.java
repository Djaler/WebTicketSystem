package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.service.ProjectService;
import com.moracle.webticketsystem.model.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    public ProjectTicketController(TicketService ticketService, ProjectService projectService) {
        this.ticketService = ticketService;
        this.projectService = projectService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        /* ИЛИ можно вот так. Тогда Principal не нужен
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken) == true) {
            return "redirect:/login";
        }
        */
        List<Project> projects = projectService.getFirst();
        return "redirect:/project/"+projects.get(0).getId();
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public String tickets(@PathVariable int id, Model model, Principal principal) {

       /* if (principal == null) {
            return "redirect:/login";
        }
*/
        /* ИЛИ можно вот так. Тогда Principal не нужен
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth instanceof AnonymousAuthenticationToken) == true) {
            return "redirect:/login";
        }
        */

        List<Project> projects = projectService.getAll();
        model.addAttribute("projects",projects);
        List<Ticket> tickets = ticketService.getByProject(projectService.getById(id));
        model.addAttribute("tickets",tickets);
        return "projectTicket";
    }


    @RequestMapping(value = "/createticket", method = RequestMethod.GET)
    public String createTicket() {
        return "createTicket";
    }
}
