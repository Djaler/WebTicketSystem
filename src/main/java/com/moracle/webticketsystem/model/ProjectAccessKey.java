package com.moracle.webticketsystem.model;

import java.io.Serializable;

/**
 * Created by boggard on 05.08.2016.
 */
public class ProjectAccessKey implements Serializable {
    static final long serialVersionUID = 1L;
    private int id_project;
    private int id_user;
    public ProjectAccessKey() {

    }

    public ProjectAccessKey(int id_project, int id_user) {
        this();
        setIdProject(id_project);
        setIdUser(id_user);
    }

    public int getIdProject() {
        return id_project;
    }

    public void setIdProject(int id_project) {
        this.id_project=id_project;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user=id_user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectAccessKey project_access_key = (ProjectAccessKey) o;

        if (id_project != project_access_key.id_project) {
            return false;
        }
        //noinspection RedundantIfStatement
        if (id_user != project_access_key.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = 31 * id_user * id_project;
        return result;
    }
}
