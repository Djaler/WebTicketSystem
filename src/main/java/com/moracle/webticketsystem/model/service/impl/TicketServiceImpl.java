package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.repository.TicketRepository;
import com.moracle.webticketsystem.model.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
@Service
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

    @Override
    public List<Ticket> getByProject(Project project) {
        return ticketRepository.findByProject(project);
    }
}
