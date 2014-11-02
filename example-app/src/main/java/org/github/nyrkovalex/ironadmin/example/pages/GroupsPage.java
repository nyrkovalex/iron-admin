package org.github.nyrkovalex.ironadmin.example.pages;

import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.example.entities.Group;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class GroupsPage extends DefaultTemplatePage<Group> {
    public GroupsPage() {
        super("Groups", "/groups", Group.class);
    }

    @NotNull
    @Override
    public Collection<Group> getEntities() {
        return Arrays.asList(
                new Group("administrators"),
                new Group("users")
        );
    }
}
