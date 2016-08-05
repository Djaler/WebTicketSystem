package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Role;

/**
 * Created by dmitry on 8/4/2016.
 */
public interface RoleService {
    Role addRole(Role role);
    Role updateRole(Role role);
    void deleteRole(int id);
}
