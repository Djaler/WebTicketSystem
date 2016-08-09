package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.repository.TicketRepository;
import com.moracle.webticketsystem.model.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by djaler on 09.08.16.
 */
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(int id) {
        ticketRepository.delete(id);
    }

    @Override
    public Ticket getById(int id) {
        return ticketRepository.findById(id);
    }
}
