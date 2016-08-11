package com.moracle.webticketsystem.model;

import com.moracle.webticketsystem.model.entity.Comment;

/**
 * Created by djaler on 10.08.16.
 */
public class CommentInfo {
    private String datetime;
    private String text;
    private String username;

    public CommentInfo(Comment comment) {
        setUsername(comment.getUser().getName());
        setText(comment.getText());
        setDatetime(comment.datetimeAsString());
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
