package org.github.nyrkovalex.ironadmin.example.groups;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;

import java.util.Arrays;
import java.util.List;

public class GroupsPage extends DefaultTemplatePage<Group> {
  public GroupsPage() {
    super(Group.class, new GroupProvider());
  }

  private static class GroupProvider implements EntityProvider<Group> {
    @Override
    public List<Group> all() {
      return Arrays.asList(
          new Group("administrators"),
          new Group("users")
      );
    }
  }
}
