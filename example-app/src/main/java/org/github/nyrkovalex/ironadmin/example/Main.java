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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Main {
    private final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {

        setupLogging();

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
                            "%s [ %s ] - %s: %s",
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
