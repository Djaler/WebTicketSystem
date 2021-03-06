package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById(int id);

    List<Comment> findByTicket(Ticket ticket);
}
