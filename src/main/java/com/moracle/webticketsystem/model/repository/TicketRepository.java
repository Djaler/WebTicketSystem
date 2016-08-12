package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.entity.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by djaler on 09.08.16.
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {
    Ticket findById(int id);
    List<Ticket> findByProject(Project project, Pageable pageable);
    int countByProject(Project project);
}
