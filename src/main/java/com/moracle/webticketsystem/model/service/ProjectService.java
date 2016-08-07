package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Project;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectService {
    Project addProject(Project project);
    Project updateProject(Project project);
    void deleteProject(int id);
    Project getByName(String name);
}