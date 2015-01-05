package org.github.nyrkovalex.ironadmin.core.servlet;

import org.github.nyrkovalex.ironadmin.core.TemplateResolver;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PageContext;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.github.nyrkovalex.ironadmin.core.pages.dummy.DummyAdminContext;
import org.github.nyrkovalex.ironadmin.core.pages.dummy.DummyFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AdminDispatcherServletResponseTest extends AdminDispatcherServletTest {

    @Mock private PageContext pageContext;
    private AdminDispatcherServlet dispatcherServlet;
    private HttpServletRequest req;
    private HttpServletResponse res;
    private PrintWriter writer;

    @Override
    @Before
    public void setUp() throws Exception {
        DummyFactory.reset();
        super.setUp();
        setAdminContextClass(DummyAdminContext.class);
        dispatcherServlet = getDispatcherServlet();
        buildRequest();
        buildResponse();
    }

    private void buildResponse() throws IOException {
        res = DummyFactory.getResponse();
        writer = mock(PrintWriter.class);
        when(res.getWriter()).thenReturn(writer);
    }

    private void buildRequest() {
        req = DummyFactory.getRequest();
        when(req.getServletPath()).thenReturn("/foo");
        when(req.getAttribute(AdminDispatcherServlet.PAGE_CONTEXT_ATTR)).thenReturn(pageContext);
    }

    @Test
    public void testShouldResolveTemplate() throws Exception {
        dispatcherServlet.doGet(req, res);
        TemplateResolver resolver = DummyFactory.getResolver();
        verify(resolver).resolvePageTemplate("/foo", pageContext, writer);
    }

    @Test
    public void testShouldAskForCorrectPageWIthLongPath() throws Exception {
        PageRegistry registry = DummyFactory.getRegistry();
        when(req.getRequestURI()).thenReturn("/foo/bar/some/stuff/here");
        when(registry.pageFor("/bar")).thenReturn(Optional.<Page>empty());
        dispatcherServlet.service(req, res);
        verify(registry).pageFor("/bar");
    }

    @Test
    public void testShouldAskForCorrectPageWithExactPath() throws Exception {
        PageRegistry registry = DummyFactory.getRegistry();
        when(req.getRequestURI()).thenReturn("/foo/bar");
        when(registry.pageFor("/bar")).thenReturn(Optional.<Page>empty());
        dispatcherServlet.service(req, res);
        verify(registry).pageFor("/bar");
    }
}
