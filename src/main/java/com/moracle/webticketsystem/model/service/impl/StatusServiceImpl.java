package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Status;
import com.moracle.webticketsystem.model.enums.StatusEnum;
import com.moracle.webticketsystem.model.repository.StatusRepository;
import com.moracle.webticketsystem.model.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by djaler on 05.08.16.
 */
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void delete(int id) {
        statusRepository.delete(id);
    }

    @Override
    public Status getByStatus(String status) {
        return statusRepository.findByStatus(status);
    }

    @PostConstruct
    public void init() {
        Map<String, Integer> idMap = new HashMap<>(StatusEnum.getStatuses().length);

        for (String status : StatusEnum.getStatuses()) {
            int statusId = statusRepository.findByStatus(status).getId();
            idMap.put(status, statusId);
        }

        StatusEnum.setIdMap(idMap);
    }
}
