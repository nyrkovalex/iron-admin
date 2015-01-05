package org.github.nyrkovalex.ironadmin.example.comments;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommentProvider implements EntityProvider<Comment, Integer> {

    public static final List<Comment> COMMENTS = Arrays.asList(
            new Comment(1, "dude@nowhere.com", "They gonna kill that poor woman", new Date()),
            new Comment(2, "walter@nowhere.com", "Fuck it, let's go bowling", new Date())
                                                              );

    @Override
    public List<Comment> all() {
        return COMMENTS;
    }

    @Override
    public Comment byId(Integer integer) {
        return COMMENTS.get(integer + 1);
    }
}
