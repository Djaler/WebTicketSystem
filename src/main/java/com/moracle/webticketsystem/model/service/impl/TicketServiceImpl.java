package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Priority;
import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.entity.Status;
import com.moracle.webticketsystem.model.entity.Ticket;
import com.moracle.webticketsystem.model.repository.TicketRepository;
import com.moracle.webticketsystem.model.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void delete(int id) {
        ticketRepository.delete(id);
    }

    @Override
    public Ticket getById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> getByProject(Project project, int page, int size) {
        return ticketRepository.findByProject(project, new PageRequest(page, size, Sort.Direction.DESC, "datetime"));
    }

    @Override
    public List<Ticket> getByProjectWithFilter(Project project, Status status, Priority priority) {
        return ticketRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(5);
            predicates.add(criteriaBuilder.equal(root.get("project"), project));
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (priority != null) {
                predicates.add(criteriaBuilder.equal(root.get("priority"), priority));
            }
            Predicate[] p = new Predicate[predicates.size()];
            for (int i = 0; i < predicates.size(); i++) {
                p[i] = predicates.get(i);
            }
            return criteriaBuilder.and(p);
        }, new Sort(Sort.Direction.ASC, "datetime"));
    }

    @Override
    public int countByProject(Project project) {
        return ticketRepository.countByProject(project);
    }


}
