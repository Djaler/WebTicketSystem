package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.CommentInfo;
import com.moracle.webticketsystem.model.entity.Attachment;
import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.entity.User;
import com.moracle.webticketsystem.model.service.AttachmentService;
import com.moracle.webticketsystem.model.service.CommentService;
import com.moracle.webticketsystem.model.service.TicketService;
import com.moracle.webticketsystem.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Inject
    @Autowired
    public TicketController(TicketService ticketService, CommentService commentService, UserService userService, Environment env, AttachmentService attachmentService) {
        this.ticketService = ticketService;
        this.commentService = commentService;
        this.userService = userService;
        this.env = env;
        this.attachmentService = attachmentService;
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
    public CommentInfo addComment(@PathVariable String id, MultipartHttpServletRequest request,
                                  Principal principal) throws IOException {
        Ticket ticket = ticketService.getById(Integer.parseInt(id));

        User currentUser = userService.getByLogin(principal.getName());

        String text = request.getParameter("text");
        MultipartFile attachedFile = request.getFile("file");

        Comment comment = new Comment(ticket, currentUser, new Date(), text);

        if (!attachedFile.isEmpty()) {
            Attachment attachment = new Attachment();
            comment.setAttachment(attachment);
        }
        commentService.save(comment);

        if (!attachedFile.isEmpty()) {
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
