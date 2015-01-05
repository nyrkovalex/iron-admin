package org.github.nyrkovalex.ironadmin.core.servlet;

import org.github.nyrkovalex.ironadmin.core.AdminContext;
import org.github.nyrkovalex.ironadmin.core.defaults.DefaultAdminContext;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PageContext;
import org.github.nyrkovalex.ironadmin.core.pages.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AdminDispatcherServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AdminDispatcherServlet.class);

    static final String CONTEXT_CLASS_PARAMETER_NAME = "context-class";
    static final String PAGE_CONTEXT_ATTR = "ia-page-context";

    private AdminContext adminContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        String contextClassName = config.getInitParameter(CONTEXT_CLASS_PARAMETER_NAME);
        if (contextClassName == null || contextClassName.isEmpty()) {
            this.adminContext = DefaultAdminContext.instance();
        } else {
            this.adminContext = loadContextForClassName(contextClassName);
        }
        super.init(config);
    }

    private static AdminContext loadContextForClassName(String className) throws ServletException {
        try {
            Class<?> contextClass = loadClass(className);
            return createInstance(contextClass);
        } catch (IllegalArgumentException e) {
            throw new ServletException("Wrong context class name provided: " + className, e);
        }
    }

    private static AdminContext createInstance(Class<?> contextClass) {
        try {
            return (AdminContext) contextClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalArgumentException(String.format("Could not instantiate %s", contextClass.getName()), e);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(
                    String.format("%s does not implement %s", contextClass.getName(), AdminContext.class.getName()), e);
        }
    }

    private static Class<?> loadClass(String className) {
        Class<?> contextClass;
        try {
            contextClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Cannot find class for name " + className, e);
        }
        return contextClass;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Got request to " + req.getRequestURI());
        PageRequest request = getPageRequest(req);
        Optional<Page> page = adminContext.pageRegistry().pageFor(request.pageUrl());
        if (page.isPresent()) {
            req.setAttribute(PAGE_CONTEXT_ATTR, page.get().pageContextForRequest(request));
            super.service(req, resp);
            return;
        }
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    private PageRequest getPageRequest(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String withoutServletPath = requestURI.replace(req.getServletPath() + "/", "");
        String[] splitted = withoutServletPath.split("/");
        String pageUrl = "/" + (splitted.length > 0 ? splitted[0] : "");
        String entityId = "" + (splitted.length > 1 ? splitted[1] : "");
        return new PageRequest(pageUrl, entityId.isEmpty() ? Optional.empty() : Optional.of(entityId));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageContext context = (PageContext) req.getAttribute(PAGE_CONTEXT_ATTR);
        adminContext.templateResolver().resolvePageTemplate(req.getServletPath(), context, resp.getWriter());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    public AdminContext getAdminContext() {
        return adminContext;
    }
}
