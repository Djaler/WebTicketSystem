package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.ProjectAccessKey;
import com.moracle.webticketsystem.model.entity.ProjectAccess;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectAccessRepository extends CrudRepository<ProjectAccess,ProjectAccessKey>{

}
