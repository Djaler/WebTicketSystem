package com.moracle.webticketsystem.model.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by djaler on 09.08.16.
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
    private int id;
    private Timestamp datetime;
    private String text;
    private Attachment attachment;
    private Ticket ticket;
    private User user;

    public Comment(Ticket ticket, User user, String date, String text) {
        this();
        setTicket(ticket);
        setUser(user);
        datetimeAsString(date);
        setText(text);
    }

    public Comment(Ticket ticket, User user, Date date, String text) {
        this();
        setTicket(ticket);
        setUser(user);
        datetimeAsDate(date);
        setText(text);
    }

    public Comment() {

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
    @Column(name = "datetime", nullable = false)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "text", length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne()
    @JoinColumn(name = "id_ticket")
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @ManyToOne()
    @JoinColumn(name = "id_author")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        Comment comment = (Comment) o;

        if (id != comment.id) {
            return false;
        }
        if (datetime != null ? !datetime.equals(comment.datetime) : comment.datetime != null) {
            return false;
        }
        //noinspection RedundantIfStatement
        if (text != null ? !text.equals(comment.text) : comment.text != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
