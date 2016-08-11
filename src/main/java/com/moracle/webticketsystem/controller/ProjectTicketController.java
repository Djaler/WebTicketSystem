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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class ProjectTicketController {


    private final TicketService ticketService;
    private final ProjectService projectService;
    private final UserService userService;
    @SuppressWarnings("FieldCanBeLocal")
    private final int ticketsOnPage = 15;

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
        return "redirect:/project/" + project.getId();
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public String tickets(@PathVariable int id, Model model) {
        List<Project> projects = projectService.getAll();
        Project selectedProject = projects
                .stream()
                .filter(project -> project.getId() == id)
                .findFirst().get();
        List<Ticket> tickets = ticketService.getByProject(projectService.getById(id), 0, ticketsOnPage);

        model.addAttribute("projects", projects);
        model.addAttribute("selectedProject", selectedProject);
        model.addAttribute("tickets", tickets);
        model.addAttribute("currentpage",0);
        return "projectTicket";
    }

    @RequestMapping(value = "/project/{id}/createticket", method = RequestMethod.POST)
    public String doCreateTicket(@PathVariable int id,
                                 @ModelAttribute TicketInfo ticketInfo,
                                 @RequestParam(value = "attachedFile", required = false) MultipartFile attachedFiles,
                                 Principal principal) throws IOException {
        Project project = projectService.getById(id);
        User user = userService.getByLogin(principal.getName());
        if (!attachedFiles.isEmpty()) {
            //TODO: создаём Attachment entity и сетим его в тикет
        }

        Ticket newTicket = new Ticket(user, project, ticketInfo.getSubject(), ticketInfo.getDescription(),
                PriorityEnum.toEnum(ticketInfo.getPriority()));
        ticketService.addTicket(newTicket);
        return "redirect: /project/" + id;
    }

    @ResponseBody
    @RequestMapping(value = "project/{id}", method = RequestMethod.POST)
    public List<TicketInfo> pages(@PathVariable int id, @RequestParam(value = "page") int page) {
        List<Ticket> tickets = ticketService.getByProject(projectService.getById(id), page, ticketsOnPage);
        List<TicketInfo> ticketInfos = new ArrayList<>();
        for (Ticket t:tickets) {
            ticketInfos.add(new TicketInfo(t));
        }
        return ticketInfos;
    }
}
