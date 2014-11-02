package org.github.nyrkovalex.ironadmin.example.users;

import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;

import java.util.Arrays;
import java.util.List;

public class UsersPage extends DefaultTemplatePage<User> {
    public UsersPage() {
        super("Users", "/users", User.class, new UserProvider());
        overridePropertyDefinitions(new PropertyDefinition("email", "E-Mail"));
    }

    private static class UserProvider implements org.github.nyrkovalex.ironadmin.core.EntityProvider<User> {
        @Override
        public List<User> all() {
            return Arrays.asList(
                    new User("dude@nowhere.com", "Jeffrey", "Lebowski"),
                    new User("walter@nowhere.com", "Walter", "Sobchak"),
                    new User("donnie@nowhere.com", "Donnie", null)
            );
        }
    }
}
