package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.repository.PriorityRepository;
import com.moracle.webticketsystem.model.repository.ProjectRepository;
import com.moracle.webticketsystem.model.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by boggard on 05.08.2016.
 */
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(int id) {
        projectRepository.delete(id);
    }

    @Override
    public Project getByName(String name) {
        return projectRepository.findByName(name);
    }
}
