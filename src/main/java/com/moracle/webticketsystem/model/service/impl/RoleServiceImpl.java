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

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        roleRepository.delete(id);
    }

    @PostConstruct
    public void init(){
        Map<String, Integer> idMap = new HashMap<>(RoleEnum.getRoles().length);

        for(String role : RoleEnum.getRoles()){
            int roleId = roleRepository.findByRole(role).getId();
            idMap.put(role, roleId);
        }

        RoleEnum.setIdMap(idMap);
    }

    @Override
    public Role getByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
