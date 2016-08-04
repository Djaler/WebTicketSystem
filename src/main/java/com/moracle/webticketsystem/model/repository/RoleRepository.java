package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dmitry on 8/4/2016.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
