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

/**
 * Created by dmitry on 8/9/2016.
 */
@Controller
public class CreateTicketController {

    private final TicketService ticketService;
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public CreateTicketController(TicketService ticketService, ProjectService projectService, UserService userService) {
        this.ticketService = ticketService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @RequestMapping(value = "/project/{id}/createticket", method = RequestMethod.GET)
    public String createTicket(@PathVariable int id, Model model) {
        Project project = projectService.getById(id);
        model.addAttribute("selectedProject", project);
        return "createTicket";
    }

    @RequestMapping(value = "/project/{id}/createticket", method = RequestMethod.POST)
    public String doCreateTicket(@PathVariable int id, @ModelAttribute TicketInfo ticketInfo, Principal principal) {
        Project project = projectService.getById(id);
        User user = userService.getByLogin(principal.getName());
        Ticket newTicket = new Ticket(user, project, ticketInfo.getSubject(),
                PriorityEnum.toEnum(ticketInfo.getPriority()));
        ticketService.addTicket(newTicket);
        return "redirect: /project/" + id;
    }
}
