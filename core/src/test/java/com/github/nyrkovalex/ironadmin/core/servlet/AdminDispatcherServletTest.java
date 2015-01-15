package com.github.nyrkovalex.ironadmin.core.servlet;

import org.junit.Before;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AdminDispatcherServletTest extends UnitTest {
    private AdminDispatcherServlet dispatcherServlet;
    private ServletConfig servletConfig;


    @Before
    public void setUp() throws Exception {
        servletConfig = fakeServletConfig();
        dispatcherServlet = new AdminDispatcherServlet();
    }

    protected AdminDispatcherServlet getDispatcherServlet() {
        return dispatcherServlet;
    }

    protected ServletConfig getServletConfig() {
        return servletConfig;
    }

    protected void setAdminContextClass(Class<?> clazz) throws ServletException {
        setAdminContextClassName(clazz.getName());
    }

    protected void setAdminContextClassName(String name) throws ServletException {
        when(getServletConfig().getInitParameter(AdminDispatcherServlet.CONTEXT_CLASS_PARAMETER_NAME)).thenReturn(name);
        getDispatcherServlet().init(getServletConfig());
    }

    private ServletConfig fakeServletConfig() {
        return mock(ServletConfig.class);
    }
}
