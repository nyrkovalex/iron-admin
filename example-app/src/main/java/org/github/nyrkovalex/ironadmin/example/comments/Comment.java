package org.github.nyrkovalex.ironadmin.example.comments;

import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
public class Comment {
    private final String user;
    private final String message;
    private final Date createdDate;

    public Comment(String user, String message, Date createdDate) {
        this.user = user;
        this.message = message;
        this.createdDate = createdDate;
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
