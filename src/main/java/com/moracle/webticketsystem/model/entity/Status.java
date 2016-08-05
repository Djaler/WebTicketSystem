package com.moracle.webticketsystem.model.entity;

import com.moracle.webticketsystem.model.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by djaler on 05.08.16.
 */
@Entity
@Table(name = "status")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String status;

    public Status(int id, StatusEnum statusEnum) {
        this(id, statusEnum.toString());
    }

    public Status(int id, String status) {
        this();
        setId(id);
        setStatus(status);
    }

    public Status(StatusEnum statusEnum) {
        this(statusEnum.toString());
    }

    public Status(String status) {
        this();
        setStatus(status);
    }

    public Status() {

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
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
            Status status1 = (Status) o;

            if (id != status1.id) {
                return false;
            }
            if (status != null ? !status.equals(status1.status) : status1.status != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
