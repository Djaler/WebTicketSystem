package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.TicketInfo;
import com.moracle.webticketsystem.model.entity.*;
import com.moracle.webticketsystem.model.enums.PriorityEnum;
import com.moracle.webticketsystem.model.enums.StatusEnum;
import com.moracle.webticketsystem.model.service.AttachmentService;
import com.moracle.webticketsystem.model.service.ProjectService;
import com.moracle.webticketsystem.model.service.TicketService;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 7/27/2016.
 */
@Controller
public class ProjectTicketController {

    private final Environment env;
    private final TicketService ticketService;
    private final ProjectService projectService;
    private final UserService userService;
    private final int ticketsOnPage = 15;
    private AttachmentService attachmentService;

    @Autowired
    @Inject
    public ProjectTicketController(Environment env, TicketService ticketService, ProjectService projectService, UserService userService, AttachmentService attachmentService) {
        this.env = env;
        this.ticketService = ticketService;
        this.projectService = projectService;
        this.userService = userService;
        this.attachmentService = attachmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal) {
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
        model.addAttribute("currentpage", 0);
        model.addAttribute("pagecount", ticketService.countByProject(selectedProject)/ticketsOnPage);
        return "projectTicket";
    }

    @RequestMapping(value = "/project/{id}/createticket", method = RequestMethod.POST)
    public String doCreateTicket(@PathVariable int id,
                                 @ModelAttribute TicketInfo ticketInfo,
                                 @RequestParam(value = "attachedFile", required = false) MultipartFile attachedFile,
                                 Principal principal) throws IOException {
        Project project = projectService.getById(id);
        User user = userService.getByLogin(principal.getName());
        Ticket newTicket = new Ticket(user, project, ticketInfo.getSubject(), ticketInfo.getDescription(),
                PriorityEnum.toEnum(ticketInfo.getPriority()));
        if (attachedFile.isEmpty() == false) {
            Attachment attachment = new Attachment();
            newTicket.setAttachment(attachment);
        }
        ticketService.save(newTicket);
        if (attachedFile.isEmpty() == false) {
            Attachment attachment = newTicket.getAttachment();
            String filePath = new String(env.getProperty("attachment.path").getBytes("ISO-8859-1"), "UTF-8")
                    + "/" + attachment.getId() + "/" + attachedFile.getOriginalFilename();
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                fileOutputStream.write(attachedFile.getBytes());
            }
            attachment.setPath(filePath);
            attachment.setSize((int) attachedFile.getSize());
            attachmentService.save(attachment);
        }
        return "redirect: /project/" + id;
    }

    @ResponseBody
    @RequestMapping(value = "project/{id}", method = RequestMethod.POST)
    public List<TicketInfo> pages(@PathVariable int id, @RequestParam(value = "page") int page) {
        List<Ticket> tickets = ticketService.getByProject(projectService.getById(id), page, ticketsOnPage);
        List<TicketInfo> ticketInfos = new ArrayList<>();
        for (Ticket t : tickets) {
            ticketInfos.add(new TicketInfo(t));
        }
        return ticketInfos;
    }

    @RequestMapping(value = "project/{id}/filter", method = RequestMethod.POST)
    public String doFilter(@PathVariable int id, @ModelAttribute("filterInfo") TicketInfo filterInfo, Model model) {
        Status status = "empty".equals(filterInfo.getStatus()) == false ?
                new Status(StatusEnum.toEnum(filterInfo.getStatus()).toID(), filterInfo.getStatus()) :
                null;
        Priority priority = "empty".equals(filterInfo.getPriority()) == false ?
                new Priority(PriorityEnum.toEnum(filterInfo.getPriority()).toID(), filterInfo.getPriority()) :
                null;

        List<Ticket> tickets = ticketService.getByProjectWithFilter(projectService.getById(id), status, priority);
        model.addAttribute("tickets", tickets);
        return "fragments/ticketTableRow";
    }
}
