package com.moracle.webticketsystem.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by dmitry on 6/29/2016.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private byte[] password;
    private String login;
    private Role role;

    /*public User(String login, String passwordHash, String name, RoleEnum roleEnum) {
        this(login, passwordHash, name);
        roleAsEnum(roleEnum);
    }*/

    public User(String login, String passwordHash, String name) {
        this(login, name);
        passwordAsString(passwordHash);
    }

    public User(String login, String passwordHash, String name, Role role) {
        this(login, name);
        passwordAsString(passwordHash);
        setRole(role);
    }

    public User(String login, byte[] password, String name, Role role) {
        this(login, password, name);
        setRole(role);
    }

    public User(String login, byte[] password, String name) {
        this(login, name);
        setPassword(password);
    }

    public User(String login, String name) {
        this();
        setLogin(login);
        setName(name);
        //roleAsEnum(RoleEnum.USER);
    }

    public User() {

    }
/*
    public void roleAsEnum(RoleEnum roleEnum) {
        setRole(new Role(roleEnum.toID(), roleEnum.toString()));
    }

    public RoleEnum roleAsEnum() {
        return RoleEnum.toEnum(getRole().getRole());
    }
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String passwordAsString() {
        return new String(getPassword());
    }

    public void passwordAsString(String pass) {
        setPassword(pass.getBytes());
    }

    @Basic
    @Column(name = "password", nullable = false)
    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @ManyToOne()
    @JoinColumn(name = "id_role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        //if (!Arrays.equals(password, user.password)) return false;
        //noinspection RedundantIfStatement
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
