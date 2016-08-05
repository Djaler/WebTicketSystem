package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.ProjectAccessKey;
import com.moracle.webticketsystem.model.entity.ProjectAccess;
import com.moracle.webticketsystem.model.repository.ProjectAccessRepository;
import com.moracle.webticketsystem.model.service.ProjectAccessService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by boggard on 05.08.2016.
 */
public class ProjectAccessServiceImpl implements ProjectAccessService{

    @Autowired
    ProjectAccessRepository projectAccessRepository;

    @Override
    public ProjectAccess addLink(int id_project, int id_user) {
        return projectAccessRepository.save(new ProjectAccess(id_project,id_user));
    }

    @Override
    public void removeLink(ProjectAccessKey key) {
        projectAccessRepository.delete(key);
    }
}
