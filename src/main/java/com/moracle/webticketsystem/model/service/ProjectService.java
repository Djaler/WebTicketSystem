package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Project;

import java.util.List;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectService {
    Project addProject(Project project);
    Project updateProject(Project project);
    void deleteProject(int id);
    Project getById(int id);
    List<Project> getAll();

    Project getFirst();
}
