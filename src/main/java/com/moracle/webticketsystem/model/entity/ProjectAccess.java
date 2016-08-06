package com.moracle.webticketsystem.model.entity;

import com.moracle.webticketsystem.model.ProjectAccessKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by boggard on 05.08.2016.
 */
@Entity
@Table(name = "project_access")
@IdClass(ProjectAccessKey.class)
public class ProjectAccess implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id_project;
    private int id_user;

    public ProjectAccess(int id_project, int id_user) {
        this();
        this.id_project=id_project;
        this.id_user=id_user;
    }

    public ProjectAccess() {

    }

    @Id
    @Column(name = "id_project", nullable = false)
    public int getIdProject() {
        return id_project;
    }

    public void setIdProject(int id_project) {
        this.id_project=id_project;
    }

    @Id
    @Column(name = "id_user", nullable = false)
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

        ProjectAccess project_access = (ProjectAccess) o;

        if (id_project != project_access.id_project) {
            return false;
        }
        //noinspection RedundantIfStatement
        if (id_user != project_access.id_user) {
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
