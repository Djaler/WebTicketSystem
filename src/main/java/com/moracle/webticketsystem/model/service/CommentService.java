package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.Ticket;

import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
public interface CommentService {
    Comment addComment(Comment comment);

    Comment updateTicket(Comment comment);

    void deleteId(int id);

    Comment getById(int id);

    List<Comment> getByTicket(Ticket ticket);
}
