package org.github.nyrkovalex.ironadmin.example.users;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.core.pages.EntityMeta;
import org.github.nyrkovalex.ironadmin.core.pages.PageMapping;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;

import java.util.Arrays;
import java.util.List;

public class UsersPage extends DefaultTemplatePage<User, String> {
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

  private static class UserProvider implements EntityProvider<User, String> {

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
    public User byId(String s) {
      return USERS.get(0);
    }
  }
}
