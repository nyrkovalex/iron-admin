package org.github.nyrkovalex.ironadmin.example;

import org.github.nyrkovalex.ironadmin.core.AdminContext;
import org.github.nyrkovalex.ironadmin.core.defaults.DefaultAdminContext;
import org.github.nyrkovalex.ironadmin.core.defaults.DefaultTemplatePage;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;
import org.github.nyrkovalex.ironadmin.example.comments.Comment;
import org.github.nyrkovalex.ironadmin.example.comments.CommentProvider;
import org.github.nyrkovalex.ironadmin.example.groups.GroupsPage;
import org.github.nyrkovalex.ironadmin.example.users.UsersPage;
import org.github.nyrkovalex.ironadmin.server.IronAdminServer;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Main {

    public static void main(String[] args) {

        setupLogging();

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

    private static void setupLogging() {
        java.util.logging.Logger rootLogger = java.util.logging.Logger.getLogger("");
        rootLogger.setLevel(Level.WARNING);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(Level.FINE);
            h.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format(
                            "%s [ %s ] - %s: %s\n",
                            new Date(record.getMillis()),
                            record.getLevel(),
                            record.getSourceClassName(),
                            record.getMessage());
                }
            });
        }
        java.util.logging.Logger.getLogger("org.github.nyrkovalex").setLevel(Level.FINE);
    }

}
