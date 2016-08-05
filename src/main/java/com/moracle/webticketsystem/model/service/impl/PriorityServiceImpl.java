package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Priority;
import com.moracle.webticketsystem.model.enums.PriorityEnum;
import com.moracle.webticketsystem.model.repository.PriorityRepository;
import com.moracle.webticketsystem.model.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by boggard on 05.08.2016.
 */
@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    private Map<String, Integer> prioritiesMap = new HashMap<>(PriorityEnum.getPriorities().length);

    @Override
    public Priority addPriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public Priority updatePriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public void deletePriority(int id) {
        priorityRepository.delete(id);
    }

    @Override
    public Priority getbyEnum(PriorityEnum priorityEnum) {
        return new Priority(prioritiesMap.get(priorityEnum.toString()),priorityEnum.toString());
    }

    @PostConstruct
    public void init(){
        for(String priority : PriorityEnum.getPriorities()){
            int priorityId = priorityRepository.findByPriority(priority).getId();
            prioritiesMap.put(priority, priorityId);
        }
    }
}
