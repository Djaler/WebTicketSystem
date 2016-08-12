package com.moracle.webticketsystem.model;

import com.moracle.webticketsystem.model.entity.Comment;

/**
 * Created by djaler on 10.08.16.
 */
public class CommentInfo {
    private String datetime;
    private String text;
    private String username;
    private String attachName;
    private int id;

    public CommentInfo(Comment comment) {
        setUsername(comment.getUser().getName());
        setText(comment.getText());
        setDatetime(comment.datetimeAsString());
        if (comment.getAttachment() != null) {
            setId(comment.getId());
            setAttachName(comment.getAttachment().getName());
        }
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
