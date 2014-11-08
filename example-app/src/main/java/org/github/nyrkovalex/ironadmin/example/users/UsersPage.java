package org.github.nyrkovalex.ironadmin.example.users;

import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.core.pages.PageUrl;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;
import org.github.nyrkovalex.ironadmin.core.pages.EntityMeta;

import java.util.Arrays;
import java.util.List;

public class UsersPage extends DefaultTemplatePage<User> {
    public UsersPage() {
        super(
                User.class,
                new UserProvider(),
                PageUrl.of("/users"),
                EntityMeta.of(
                        "Admin Users",
                        Arrays.asList(
                                new PropertyDefinition("email", "E-Mail")
                        ),
                        Arrays.asList(
                                "password"
                        )
                ));
    }

    private static class UserProvider implements org.github.nyrkovalex.ironadmin.core.EntityProvider<User> {
        @Override
        public List<User> all() {
            return Arrays.asList(
                    new User("dude@nowhere.com", "Jeffrey", "Lebowski", "secret"),
                    new User("walter@nowhere.com", "Walter", "Sobchak", "secret"),
                    new User("donnie@nowhere.com", "Donnie", null, "secret")
            );
        }
    }
}
