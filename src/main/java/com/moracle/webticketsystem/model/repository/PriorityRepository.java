package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.Priority;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by boggard on 05.08.2016.
 */
public interface PriorityRepository extends CrudRepository<Priority,Integer> {
    Priority findByPriority(String priority);
}
