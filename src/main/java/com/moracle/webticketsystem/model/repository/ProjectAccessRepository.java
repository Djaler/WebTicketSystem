package com.moracle.webticketsystem.model.repository;

import com.moracle.webticketsystem.model.ProjectAccessKey;
import com.moracle.webticketsystem.model.entity.ProjectAccess;
import com.moracle.webticketsystem.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectAccessRepository extends CrudRepository<ProjectAccess, ProjectAccessKey> {
    @Query("select u from ProjectAccess pa join User u on pa.idUser = u.id where pa.idProject = :id order by u.name asc")
    List<User> getUsersByProjectId(@Param(value = "id") int id);
}
