package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.repository.CommentRepository;
import com.moracle.webticketsystem.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        commentRepository.delete(id);
    }

    @Override
    public Comment getById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getByTicket(Ticket ticket) {
        return commentRepository.findByTicket(ticket);
    }
}
