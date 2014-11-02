package org.github.nyrkovalex.ironadmin.example.pages;

import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;
import org.github.nyrkovalex.ironadmin.example.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

public class UsersPage extends DefaultTemplatePage<User> {
    public UsersPage() {
        super("Users", "/users", User.class);
        overridePropertyDefinitions(new PropertyDefinition("email", "E-Mail"));
    }

    @NotNull
    @Override
    public Collection<User> getEntities() {
        return Arrays.asList(
                new User("dude@nowhere.com", "Jeffrey", "Lebowski"),
                new User("walter@nowhere.com", "Walter", "Sobchak"),
                new User("donnie@nowhere.com", "Donnie", null)
        );
    }
}
