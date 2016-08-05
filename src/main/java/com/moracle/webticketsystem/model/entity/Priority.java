package com.moracle.webticketsystem.model.entity;

import com.moracle.webticketsystem.model.enums.PriorityEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by boggard on 05.08.2016.
 */
@Entity
@Table(name = "priority")
public class Priority implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String priority;

    public Priority(int id, PriorityEnum priorityEnum) {
        this(id, priorityEnum.toString());
    }

    public Priority(int id, String priority) {
        this();
        setId(id);
        setPriority(priority);
    }

    public Priority(PriorityEnum priorityEnum) {
        this(priorityEnum.toString());
    }

    public Priority(String priority) {
        this();
        setPriority(priority);
    }

    public Priority() {

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
    @Column(name = "priority", nullable = false, length = 20)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass() && o.getClass() != Priority.class) {
            return false;
        }

        if (getClass() == o.getClass()) {
            Priority prior = (Priority) o;

            if (id != prior.id) {
                return false;
            }
            if (!prior.equals(prior.priority)) {
                return false;
            }
        }

        if (o.getClass() == PriorityEnum.class) {
            //noinspection ConstantConditions
            PriorityEnum prior = (PriorityEnum) o;
            if (!getPriority().equals(prior.toString())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }
}
