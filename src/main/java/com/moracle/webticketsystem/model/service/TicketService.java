package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Ticket;

/**
 * Created by djaler on 09.08.16.
 */
public interface TicketService {
    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(int id);

    Ticket getById(int id);
}
