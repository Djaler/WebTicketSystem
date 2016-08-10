package com.moracle.webticketsystem.model.service.impl;

import com.moracle.webticketsystem.model.entity.Project;
import com.moracle.webticketsystem.model.repository.ProjectRepository;
import com.moracle.webticketsystem.model.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by boggard on 05.08.2016.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

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
    public Project getById(int id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Project getFirst() {
        return projectRepository.findTop1ByOrderByNameAsc();
    }
}
