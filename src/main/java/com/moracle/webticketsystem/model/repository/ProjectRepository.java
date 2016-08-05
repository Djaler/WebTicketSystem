package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findByName(String name);
}
