package com.github.nyrkovalex.ironadmin.core.servlet;

import com.github.nyrkovalex.ironadmin.core.defaults.DefaultAdminContext;
import com.github.nyrkovalex.ironadmin.core.pages.dummy.DummyAdminContext;
import org.junit.Test;

import javax.servlet.ServletException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class AdminDispatcherServletInitTest extends AdminDispatcherServletTest {

    @Test
    public void testShouldUseDefaultContext() throws Exception {
        getDispatcherServlet().init(getServletConfig());
        assertThat(getDispatcherServlet().getAdminContext(), instanceOf(DefaultAdminContext.class));
    }

    @Test
    public void testShouldUseProvidedContext() throws Exception {
        setAdminContextClass(DummyAdminContext.class);
        assertThat(getDispatcherServlet().getAdminContext(), instanceOf(DummyAdminContext.class));
    }

    @Test(expected = ServletException.class)
    public void testShouldThrowIfBadClassNameProvided() throws Exception {
        setAdminContextClassName("BAD");
    }

    @Test(expected = ServletException.class)
    public void testShouldThrowIfClassDoesNotImplementAdminContext() throws Exception {
        setAdminContextClass(Object.class);
    }

    @Test(expected = ServletException.class)
    public void testShouldThrowIfContextClassHasNoDefaultConstructor() throws Exception {
        setAdminContextClass(TestAdminContextNoDefaultCtor.class);
    }


    public static class TestAdminContextNoDefaultCtor extends DummyAdminContext {

        @SuppressWarnings("UnusedParameters")
        public TestAdminContextNoDefaultCtor(String foo) {
            // For test purpose
        }
    }
}
