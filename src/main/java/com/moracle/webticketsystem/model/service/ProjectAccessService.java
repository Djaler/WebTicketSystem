package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.entity.ProjectAccess;
import com.moracle.webticketsystem.model.entity.User;

import java.util.List;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectAccessService {
    ProjectAccess addLink(int id_project, int id_user);

    void removeLink(int id_project, int id_user);

    List<User> getUsersByProjectId(int id);
}
