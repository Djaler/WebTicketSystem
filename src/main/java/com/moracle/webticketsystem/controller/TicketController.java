package com.moracle.webticketsystem.controller;

import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.service.CommentService;
import com.moracle.webticketsystem.model.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
@Controller
public class TicketController {
    private final TicketService ticketService;
    private final CommentService commentService;

    @Autowired
    public TicketController(TicketService ticketService, CommentService commentService) {
        this.ticketService = ticketService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.GET)
    public String tickets(@PathVariable String id, Model model) {
        Ticket ticket = ticketService.getById(Integer.parseInt(id));
        model.addAttribute("ticket", ticket);

        List<Comment> commentList = commentService.getByTicket(ticket);
        model.addAttribute("comments", commentList);
        return "ticket";
    }
}
