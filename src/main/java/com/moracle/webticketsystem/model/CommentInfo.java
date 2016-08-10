package com.moracle.webticketsystem.model;

import com.moracle.webticketsystem.model.entity.Comment;
import com.moracle.webticketsystem.model.entity.User;

/**
 * Created by djaler on 10.08.16.
 */
public class CommentInfo {
    private String datetime;
    private String text;
    private User user;

    public CommentInfo(Comment comment) {
        setUser(comment.getUser());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
