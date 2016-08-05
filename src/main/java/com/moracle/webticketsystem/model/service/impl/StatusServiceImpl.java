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

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status updateStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void deleteStatus(int id) {
        statusRepository.delete(id);
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
