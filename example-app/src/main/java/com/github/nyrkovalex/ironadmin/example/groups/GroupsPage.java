package com.github.nyrkovalex.ironadmin.example.groups;

import com.github.nyrkovalex.ironadmin.core.EntityProvider;
import com.github.nyrkovalex.ironadmin.core.defaults.DefaultTemplatePage;
import com.github.nyrkovalex.ironadmin.core.pages.EntityMeta;

import java.util.Arrays;
import java.util.List;

public class GroupsPage extends DefaultTemplatePage<Group> {
    public GroupsPage() {
        super(Group.class,
              new GroupProvider(),
              EntityMeta.of(Group.class)
                      .idPropertyName("name")
                      .build()
        );
    }

    private static class GroupProvider implements EntityProvider<Group> {

        public static final List<Group> GROUPS = Arrays.asList(
                new Group("administrators"),
                new Group("users")
        );

        @Override
        public List<Group> all() {
            return GROUPS;
        }

        @Override
        public Group one(String s) {
            return GROUPS.get(0);
        }
    }
}
