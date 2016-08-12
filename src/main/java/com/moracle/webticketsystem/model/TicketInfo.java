package com.moracle.webticketsystem.model;

import com.moracle.webticketsystem.model.entity.Ticket;

/**
 * Created by dmitry on 8/9/2016.
 */
public class TicketInfo {
    private int id;
    private String subject;
    private String description;
    private String status;
    private String priority;
    private String dateAsString;
    private String assigneeName;
    private String datetime;

    public TicketInfo(Ticket ticket) {
        this.id = ticket.getId();
        this.subject = ticket.getSubject();
        this.status = ticket.getStatus().getStatus();
        this.priority = ticket.getPriority().getPriority();
        this.dateAsString = ticket.datetimeAsString();
        if(ticket.getAssignee()!=null) {
            this.assigneeName = ticket.getAssignee().getName();
        }
        else {
            this.assigneeName = "";
        }
    }

    public TicketInfo() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateAsString() {

        return dateAsString;
    }

    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
