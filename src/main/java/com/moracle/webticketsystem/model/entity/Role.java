package com.moracle.webticketsystem.model.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dmitry on 6/29/2016.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String role;

    /*public Role(int id, RoleEnum roleEnum) {
        this(id, roleEnum.toString());
    }
*/
    public Role(int id, String role) {
        this();
        setId(id);
        setRole(role);
    }

   /* public Role(RoleEnum roleEnum) {
        this(roleEnum.toString());
    }*/

    public Role(String role) {
        this();
        setRole(role);
    }

    public Role() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 20)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (getClass() == o.getClass()) {
            Role role1 = (Role) o;

            if (id != role1.id) {
                return false;
            }
            if (role != null ? !role.equals(role1.role) : role1.role != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
