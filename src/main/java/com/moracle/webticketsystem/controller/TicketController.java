package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.CommentInfo;
import com.moracle.webticketsystem.model.entity.Attachment;
import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.service.CommentService;
import com.moracle.webticketsystem.model.service.TicketService;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    private final TicketService ticketService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, CommentService commentService, UserService userService) {
        this.ticketService = ticketService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
    public String tickets(@PathVariable String id, Model model) {
        Ticket ticket = ticketService.getById(Integer.parseInt(id));
        model.addAttribute("ticket", ticket);

        List<Comment> commentList = commentService.getByTicket(ticket);
        model.addAttribute("comments", commentList);
        return "ticket";
    }

    @ResponseBody
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.POST)
    public CommentInfo addComment(@PathVariable String id, @RequestParam(value = "text") String text,
                                  Principal principal) {
        Ticket ticket = ticketService.getById(Integer.parseInt(id));

        User currentUser = userService.getByLogin(principal.getName());

        Comment comment = new Comment(ticket, currentUser, new Date(), text);
        commentService.save(comment);

        return new CommentInfo(comment);
    }

    @RequestMapping(value = "/comment/{id}/downloadattach", method = RequestMethod.GET)
    public void downloadCommentAttach(@PathVariable String id, HttpServletResponse response) throws IOException {
        Comment comment = commentService.getById(Integer.parseInt(id));
        sendFileToResponse(comment.getAttachment(), response);
    }

    @RequestMapping(value = "/ticket/{id}/downloadattach", method = RequestMethod.GET)
    public void downloadTicketAttach(@PathVariable String id, HttpServletResponse response) throws IOException {
        Ticket ticket = ticketService.getById(Integer.parseInt(id));
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
