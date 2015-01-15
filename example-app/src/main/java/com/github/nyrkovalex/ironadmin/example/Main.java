package com.github.nyrkovalex.ironadmin.example;

import com.github.nyrkovalex.ironadmin.core.AdminContext;
import com.github.nyrkovalex.ironadmin.core.defaults.DefaultAdminContext;
import com.github.nyrkovalex.ironadmin.core.defaults.DefaultTemplatePage;
import com.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import com.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;
import com.github.nyrkovalex.ironadmin.example.comments.Comment;
import com.github.nyrkovalex.ironadmin.example.comments.CommentProvider;
import com.github.nyrkovalex.ironadmin.example.groups.GroupsPage;
import com.github.nyrkovalex.ironadmin.example.users.UsersPage;
import com.github.nyrkovalex.ironadmin.server.IronAdminServer;
import com.github.nyrkovalex.seed.core.Seed;


public class Main {

    public static void main(String[] args) {

        Seed.Logging.init(true, "com.github.nyrkovalex.ironadmin");

        AdminContext context = DefaultAdminContext.instance();
        context.setTitle("Example Admin");

        PageRegistry pageRegistry = context.pageRegistry();
        pageRegistry.register(
                new UsersPage(),
                new GroupsPage(),
                DefaultTemplatePage.of(Comment.class)
                        .titled("Comments")
                        .putAt("/comments")
                        .skip("id")
                        .override(new PropertyDefinition("createdDate", "Posted On"))
                        .backedBy(new CommentProvider())
                        .build());

        IronAdminServer server = new IronAdminServer(8080, "/admin/*");
        server.start();
    }

}
