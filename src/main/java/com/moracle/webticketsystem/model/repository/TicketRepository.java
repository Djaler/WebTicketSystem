package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djaler on 09.08.16.
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findById(int id);
}
