package com.github.nyrkovalex.ironadmin.server;

import com.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class IronAdminServer {
    public static final String STATIC_RESOURCE_DIR = "ui";

    private static final String DEFAULT_MAPPING = "/admin/*";
    private static final Logger LOG = LoggerFactory.getLogger(IronAdminServer.class);
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
            LOG.info("Started Iron Admin server at port {}", port);
            server.join();
        } catch (Exception e) {
            LOG.error("Failed to start Iron Admin server. See the cause for details", e);
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
