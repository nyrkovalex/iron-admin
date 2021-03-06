package com.github.nyrkovalex.ironadmin.example.users;

import com.github.nyrkovalex.ironadmin.core.EntityProvider;
import com.github.nyrkovalex.ironadmin.core.defaults.DefaultTemplatePage;
import com.github.nyrkovalex.ironadmin.core.pages.EntityMeta;
import com.github.nyrkovalex.ironadmin.core.pages.PageMapping;
import com.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;

import java.util.Arrays;
import java.util.List;

public class UsersPage extends DefaultTemplatePage<User> {

    public UsersPage() {
        super(
                User.class,
                new UserProvider(),
                PageMapping.of("/users"),
                EntityMeta.of(User.class)
                        .title("Admin Users")
                        .overrides(
                                new PropertyDefinition("email", "E-Mail"))
                        .skips("password", "email")
                        .idPropertyName("email")
                        .build()
             );
    }

    private static class UserProvider implements EntityProvider<User> {

        public static final List<User> USERS = Arrays.asList(
                new User("dude@nowhere.com", "Jeffrey", "Lebowski", "secret"),
                new User("walter@nowhere.com", "Walter", "Sobchak", "secret"),
                new User("donnie@nowhere.com", "Donnie", null, "secret")
                                                            );

        @Override
        public List<User> all() {
            return USERS;
        }

        @Override
        public User one(String s) {
            return USERS.get(0);
        }
    }
}
