package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Priority;

/**
 * Created by boggard on 05.08.2016.
 */
public interface PriorityService {
    Priority save(Priority priority);
    void delete(int id);

    Priority getByPriority(String priority);
}
