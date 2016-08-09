package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.entity.Ticket;

import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
public interface TicketService {
    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    void deleteTicket(int id);

    Ticket getById(int id);

    List<Ticket> getByProject(Project project);
}
