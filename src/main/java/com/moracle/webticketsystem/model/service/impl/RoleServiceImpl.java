package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Role;
import com.moracle.webticketsystem.model.enums.RoleEnum;
import com.moracle.webticketsystem.model.repository.RoleRepository;
import com.moracle.webticketsystem.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitry on 8/4/2016.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private Map<String, Integer> rolesMap = new HashMap<>(RoleEnum.getRoles().length);

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.delete(id);
    }

    @Override
    public Role getByEnum(RoleEnum roleEnum) {
        return new Role(rolesMap.get(roleEnum.toString()), roleEnum.toString());
    }

    @PostConstruct
    public void init(){
        for(String role : RoleEnum.getRoles()){
            int roleId = roleRepository.findByRole(role).getId();
            rolesMap.put(role, roleId);
        }
    }
}
