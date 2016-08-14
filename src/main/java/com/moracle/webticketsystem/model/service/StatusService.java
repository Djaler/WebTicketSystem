package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Status;

/**
 * Created by djaler on 05.08.16.
 */
public interface StatusService {
    Status save(Status status);
    void delete(int id);

    Status getByStatus(String status);
}
