package org.github.nyrkovalex.ironadmin.example.groups;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.core.pages.EntityMeta;

import java.util.Arrays;
import java.util.List;

public class GroupsPage extends DefaultTemplatePage<Group, String> {
    public GroupsPage() {
        super(Group.class,
              new GroupProvider(),
              EntityMeta.of(Group.class)
                      .idPropertyName("name")
                      .build()
             );
    }

    private static class GroupProvider implements EntityProvider<Group, String> {

        public static final List<Group> GROUPS = Arrays.asList(
                new Group("administrators"),
                new Group("users")
                                                              );

        @Override
        public List<Group> all() {
            return GROUPS;
        }

        @Override
        public Group byId(String s) {
            return GROUPS.get(0);
        }
    }
}
