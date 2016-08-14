package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.CommentInfo;
import com.moracle.webticketsystem.model.entity.Attachment;
import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
@Controller
public class TicketController {
    private final Environment env;
    private final TicketService ticketService;
    private final CommentService commentService;
    private final UserService userService;
    private final AttachmentService attachmentService;
    private final PriorityService priorityService;
    private final StatusService statusService;

    @Inject
    @Autowired
    public TicketController(TicketService ticketService, CommentService commentService, UserService userService,
                            Environment env, AttachmentService attachmentService, PriorityService priorityService,
                            StatusService statusService) {
        this.ticketService = ticketService;
        this.commentService = commentService;
        this.userService = userService;
        this.env = env;
        this.attachmentService = attachmentService;
        this.priorityService = priorityService;
        this.statusService = statusService;
    }

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
    public String tickets(@PathVariable int id, Model model) {
        Ticket ticket = ticketService.getById(id);
        model.addAttribute("ticket", ticket);

        List<Comment> commentList = commentService.getByTicket(ticket);
        model.addAttribute("comments", commentList);
        return "ticket";
    }

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.POST)
    public void changeParam(@PathVariable int id, @RequestParam(value = "parameter") String parameter,
                            @RequestParam(value = "value") String value) {
        Ticket ticket = ticketService.getById(id);
        if (parameter.equals("priority.priority")) {
            ticket.setPriority(priorityService.getByPriority(value));
        } else if (parameter.equals("status.status")) {
            ticket.setStatus(statusService.getByStatus(value));
        }
        ticketService.save(ticket);
    }

    @ResponseBody
    @RequestMapping(value = "/ticket/{id}/assign_self", method = RequestMethod.POST)
    public byte[] assignSelf(@PathVariable int id, Principal principal) {
        Ticket ticket = ticketService.getById(id);
        User assignee = userService.getByLogin(principal.getName());
        ticket.setAssignee(assignee);
        ticketService.save(ticket);
        return assignee.getName().getBytes();
    }

    @ResponseBody
    @RequestMapping(value = "/ticket/{id}/add_comment", method = RequestMethod.POST)
    public CommentInfo addComment(@PathVariable int id, MultipartHttpServletRequest request,
                                  Principal principal) throws IOException {
        Ticket ticket = ticketService.getById(id);

        User currentUser = userService.getByLogin(principal.getName());

        String text = request.getParameter("text");
        MultipartFile attachedFile = request.getFile("file");

        Comment comment = new Comment(ticket, currentUser, new Date(), text);

        if (attachedFile != null) {
            Attachment attachment = new Attachment();
            comment.setAttachment(attachment);
        }
        commentService.save(comment);

        if (attachedFile != null) {
            Attachment attachment = comment.getAttachment();
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

        return new CommentInfo(comment);
    }

    @RequestMapping(value = "/comment/{id}/downloadattach", method = RequestMethod.GET)
    public void downloadCommentAttach(@PathVariable int id, HttpServletResponse response) throws IOException {
        Comment comment = commentService.getById(id);
        sendFileToResponse(comment.getAttachment(), response);
    }

    @RequestMapping(value = "/ticket/{id}/downloadattach", method = RequestMethod.GET)
    public void downloadTicketAttach(@PathVariable int id, HttpServletResponse response) throws IOException {
        Ticket ticket = ticketService.getById(id);
        sendFileToResponse(ticket.getAttachment(), response);
    }

    private void sendFileToResponse(Attachment attachment, HttpServletResponse response) throws IOException {
        File file = new File(attachment.getPath());
        byte[] bFile = new byte[(int) file.length()];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(bFile);
        }
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType(mimeType);
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
        response.setContentLength((int) file.length());
        FileCopyUtils.copy(new ByteArrayInputStream(bFile), response.getOutputStream());
    }
}
