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

    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

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

    @PostConstruct
    public void init(){
        Map<String, Integer> idMap = new HashMap<>(PriorityEnum.getPriorities().length);

        for(String priority : PriorityEnum.getPriorities()){
            int priorityId = priorityRepository.findByPriority(priority).getId();
            idMap.put(priority, priorityId);
        }

        PriorityEnum.setIdMap(idMap);
    }
}
