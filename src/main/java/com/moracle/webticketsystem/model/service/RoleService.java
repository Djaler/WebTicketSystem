package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Role;

/**
 * Created by dmitry on 8/4/2016.
 */
public interface RoleService {
    Role save(Role role);

    void delete(int id);

    Role getByRole(String role);
}
