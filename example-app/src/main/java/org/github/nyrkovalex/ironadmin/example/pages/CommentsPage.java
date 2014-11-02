package org.github.nyrkovalex.ironadmin.example.pages;

import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.example.entities.Comment;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

public class CommentsPage extends DefaultTemplatePage<Comment> {

    public CommentsPage() {
        super("Comments", "/comments", Comment.class);
    }

    @NotNull
    @Override
    public Collection<Comment> getEntities() {
        return Arrays.asList(
                new Comment("dude@nowhere.com", "They gonna kill that poor woman", new Date()),
                new Comment("walter@nowhere.com", "Fuck it, let's go bowling", new Date())
        );
    }
}
