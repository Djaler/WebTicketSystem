package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Priority;
import com.moracle.webticketsystem.model.enums.PriorityEnum;

/**
 * Created by boggard on 05.08.2016.
 */
public interface PriorityService {
    Priority addPriority(Priority priority);
    Priority updatePriority(Priority priority);
    void deletePriority(int id);
    Priority getByEnum(PriorityEnum priorityEnum);

}
