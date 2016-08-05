package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Status;

/**
 * Created by djaler on 05.08.16.
 */
public interface StatusService {
    Status addStatus(Status status);

    Status updateStatus(Status status);

    void deleteStatus(int id);
}
