package com.moracle.webticketsystem.model.entity;

import com.moracle.webticketsystem.model.enums.PriorityEnum;
import com.moracle.webticketsystem.model.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by djaler on 05.08.16.
 */
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
    private int id;
    private String subject;
    private Timestamp datetime;
    private String description;
    private Project project;
    private Status status;
    private Priority priority;
    private User owner;
    private User assignee;
    private Attachment attachment;

    public Ticket(User owner, Project project, String subject, String description, PriorityEnum priorityEnum, String date) {
        this(owner, project, subject, priorityEnum);
        setDescription(description);
        datetimeAsString(date);
    }

    public Ticket(User owner, Project project, String subject, String description, PriorityEnum priorityEnum, Date date) {
        this(owner, project, subject, priorityEnum, date);
        setDescription(description);
    }

    public Ticket(User owner, Project project, String subject, String description, PriorityEnum priorityEnum) {
        this(owner, project, subject, priorityEnum);
        setDescription(description);
    }

    public Ticket(User owner, Project project, String subject, PriorityEnum priorityEnum, String date) {
        this(owner, project, subject, priorityEnum);
        datetimeAsString(date);
    }

    public Ticket(User owner, Project project, String subject, PriorityEnum priorityEnum, Date date) {
        this(owner, project, subject, priorityEnum);
        datetimeAsDate(date);
    }

    public Ticket(User owner, Project project, String subject, PriorityEnum priorityEnum) {
        this();
        setOwner(owner);
        setProject(project);
        setSubject(subject);
        priorityAsEnum(priorityEnum);
        statusAsEnum(StatusEnum.NEW);
        datetimeAsDate(new Date());
    }

    public Ticket() {
    }

    public void statusAsEnum(StatusEnum statusEnum) {
        setStatus(new Status(statusEnum.toID(), statusEnum.toString()));
    }

    public StatusEnum statusAsEnum() {
        return StatusEnum.toEnum(getStatus().getStatus());
    }

    public void priorityAsEnum(PriorityEnum priorityEnum) {
        setPriority(new Priority(priorityEnum.toID(), priorityEnum.toString()));
    }

    public PriorityEnum priorityAsEnum() {
        return PriorityEnum.toEnum(getPriority().getPriority());
    }

    public Date datetimeAsDate() {
        return new Date(getDatetime().getTime());
    }

    public void datetimeAsDate(Date date) {
        setDatetime(new Timestamp(date.getTime()));
    }

    public String datetimeAsString() {
        return dateFormat.format(datetimeAsDate());
    }

    public void datetimeAsString(String date) {
        Date d;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Date format is incorrect");
        }
        datetimeAsDate(d);
    }

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
    @Column(name = "subject", nullable = false, length = -1)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "datetime", nullable = false)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "description", length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne()
    @JoinColumn(name = "id_owner")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToOne()
    @JoinColumn(name = "id_assignee")
    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @ManyToOne()
    @JoinColumn(name = "id_project")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne()
    @JoinColumn(name = "id_status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne()
    @JoinColumn(name = "id_priority")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "id_attachment")
    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) {
            return false;
        }
        if (subject != null ? !subject.equals(ticket.subject) : ticket.subject != null) {
            return false;
        }
        if (datetime != null ? !datetime.equals(ticket.datetime) : ticket.datetime != null) {
            return false;
        }
        //noinspection RedundantIfStatement
        if (description != null ? !description.equals(ticket.description) : ticket.description != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
