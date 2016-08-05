package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.Status;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by djaler on 05.08.16.
 */
public interface StatusRepository extends CrudRepository<Status, Integer> {
    Status findByStatus(String status);
}
