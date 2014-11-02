package org.github.nyrkovalex.ironadmin.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet;

import java.net.URL;

public class IronAdminServer {
    public static final String STATIC_RESOURCE_DIR = "ui";

    private static final String DEFAULT_MAPPING = "/admin/*";
    private final int port;
    private String mapping;

    public IronAdminServer(int port) {
        this(port, DEFAULT_MAPPING);
    }

    public IronAdminServer(int port, String mapping) {
        this.port = port;
        this.mapping = mapping;
    }

    public void start() {
        Server server = createServer();
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start server. See the cause for details.", e);
        }
    }

    private Server createServer() {
        Server server = new Server(port);
        Handler servletHandler = createServletHandler();
        server.setHandler(servletHandler);
        return server;
    }

    private Handler createServletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler();
        URL resourceUri = getClass().getClassLoader().getResource(STATIC_RESOURCE_DIR);
        if (resourceUri == null) {
            throw new IllegalStateException("Static resources directory was not found in classpath:"
                    + STATIC_RESOURCE_DIR);
        } else {
            String staticDir = resourceUri.toExternalForm();
            servletHandler.setResourceBase(staticDir);
            servletHandler.setContextPath(".");
            servletHandler.addServlet(DefaultServlet.class, "/");
        }
        servletHandler.addServlet(AdminDispatcherServlet.class, mapping);
        return servletHandler;
    }
}
