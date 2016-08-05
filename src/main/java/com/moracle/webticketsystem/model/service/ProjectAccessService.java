package com.moracle.webticketsystem.model.service;

import com.moracle.webticketsystem.model.ProjectAccessKey;
import com.moracle.webticketsystem.model.entity.ProjectAccess;

/**
 * Created by boggard on 05.08.2016.
 */
public interface ProjectAccessService {
    ProjectAccess addLink(int id_project, int id_user);
    void removeLink(ProjectAccessKey key);
}
