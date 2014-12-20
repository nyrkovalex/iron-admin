package org.github.nyrkovalex.ironadmin.core.servlet;

import org.github.nyrkovalex.ironadmin.core.AdminContext;
import org.github.nyrkovalex.ironadmin.core.defaults.DefaultContext;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
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
  static final String PAGE_ATTR_NAME = "ia-pages";

  private AdminContext adminContext;

  @Override
  public void init(ServletConfig config) throws ServletException {
    String contextClassName = config.getInitParameter(CONTEXT_CLASS_PARAMETER_NAME);
    if (contextClassName == null || contextClassName.isEmpty()) {
      this.adminContext = DefaultContext.getInstance();
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
    String pageUrl = getPageUrl(req);
    Optional<Page> page = adminContext.getRegistry().getPage(pageUrl);
    if (page.isPresent()) {
      req.setAttribute(PAGE_ATTR_NAME, page.get());
      super.service(req, resp);
      return;
    }
    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
  }

  private String getPageUrl(HttpServletRequest req) {
    String requestURI = req.getRequestURI();
    String withoutServletPath = requestURI.replace(req.getServletPath(), "");
    int secondSlashIndex = withoutServletPath.indexOf('/', 1);
    return secondSlashIndex < 0
        ? withoutServletPath
        : withoutServletPath.substring(0, secondSlashIndex);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Page page = (Page) req.getAttribute(PAGE_ATTR_NAME);
    adminContext.getTemplateResolver().resolvePageTemplate(req.getServletPath(), page, resp.getWriter());
    resp.setStatus(HttpServletResponse.SC_OK);
  }

  public AdminContext getAdminContext() {
    return adminContext;
  }
}
