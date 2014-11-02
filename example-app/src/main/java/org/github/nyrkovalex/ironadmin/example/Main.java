package org.github.nyrkovalex.ironadmin.example;

import org.github.nyrkovalex.ironadmin.core.AdminContext;
import org.github.nyrkovalex.ironadmin.core.defaults.DefaultContext;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.github.nyrkovalex.ironadmin.example.pages.CommentsPage;
import org.github.nyrkovalex.ironadmin.example.pages.GroupsPage;
import org.github.nyrkovalex.ironadmin.example.pages.UsersPage;
import org.github.nyrkovalex.ironadmin.server.IronAdminServer;

public class Main {
    public static void main(String... args) {
        AdminContext context = DefaultContext.getInstance();
        context.setTitle("Example Admin");
        PageRegistry pageRegistry = context.getRegistry();
        pageRegistry.register(
                new UsersPage(),
                new GroupsPage(),
                new CommentsPage()
        );
        IronAdminServer server = new IronAdminServer(8080, "/example/admin/*");
        server.start();
    }

}
