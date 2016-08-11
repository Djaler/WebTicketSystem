package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.Project;

import java.util.List;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectService {
    Project save(Project project);

    void delete(int id);
    Project getById(int id);
    List<Project> getAll();
    Project getFirst();
}
