package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;

import static org.mockito.Mockito.mock;

@SuppressWarnings("ConstantConditions")
public class ThymeleafTemplateResolverTest {

    private ThymeleafTemplateResolver resolver;

    @Before
    public void setUp() throws Exception {
        resolver = new ThymeleafTemplateResolver(mock(IronAdminEnvironment.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullPage() throws Exception {
        resolver.resolvePageTemplate("/foo", null, mock(PrintWriter.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullWriter() throws Exception {
        resolver.resolvePageTemplate("/foo", mock(Page.class), null);
    }
}
