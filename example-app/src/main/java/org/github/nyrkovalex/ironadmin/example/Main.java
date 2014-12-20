package org.github.nyrkovalex.ironadmin.example;

import org.github.nyrkovalex.ironadmin.core.AdminContext;
import org.github.nyrkovalex.ironadmin.core.defaults.DefaultContext;
import org.github.nyrkovalex.ironadmin.core.pages.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;
import org.github.nyrkovalex.ironadmin.example.comments.Comment;
import org.github.nyrkovalex.ironadmin.example.comments.CommentProvider;
import org.github.nyrkovalex.ironadmin.example.groups.GroupsPage;
import org.github.nyrkovalex.ironadmin.example.users.UsersPage;
import org.github.nyrkovalex.ironadmin.server.IronAdminServer;

public class Main {
  public static void main(String... args) {
    AdminContext context = DefaultContext.getInstance();
    context.setTitle("Example Admin");

    PageRegistry pageRegistry = context.getRegistry();
    pageRegistry.register(
        new UsersPage(),
        new GroupsPage(),
        DefaultTemplatePage
            .<Comment, Integer>of(Comment.class)
            .titled("Comments")
            .putAt("/comments")
            .skip("id")
            .override(new PropertyDefinition("createdDate", "Posted On"))
            .backedBy(new CommentProvider())
            .build()
    );

    IronAdminServer server = new IronAdminServer(8080, "/admin/*");
    server.start();
  }

}
