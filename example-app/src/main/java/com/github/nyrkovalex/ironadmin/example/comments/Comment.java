package com.github.nyrkovalex.ironadmin.example.comments;

import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
public class Comment {
    private final String user;
    private final String message;
    private final Date createdDate;
    private final int id;

    public Comment(int id, String user, String message, Date createdDate) {
        this.user = user;
        this.message = message;
        this.createdDate = createdDate;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
