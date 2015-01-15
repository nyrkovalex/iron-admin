package com.github.nyrkovalex.ironadmin.core.defaults;

import com.github.nyrkovalex.ironadmin.core.EntityProvider;
import com.github.nyrkovalex.ironadmin.core.SampleBean;
import com.github.nyrkovalex.ironadmin.core.pages.PageRequest;
import com.github.nyrkovalex.ironadmin.core.servlet.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class DefaultTemplatePageTest extends UnitTest {

    @Mock private EntityProvider provider;
    @Mock private PageRequest request;

    private DefaultTemplatePage page;

    @Before
    public void setUp() throws Exception {
        page = new DefaultTemplatePage<>(SampleBean.class, provider);
        when(request.pageUrl()).thenReturn("/sample-beans");
        when(request.entityId()).thenReturn(Optional.empty());
    }

    @Test
    public void testShouldCreateContextWithCorrectTitle() throws Exception {
        final DefaultPageContext result = page.pageContextForRequest(request);
        assertThat(result.getTitle(), is("Sample Bean"));
    }

    @Test
    public void testShouldCreateContextWithEntityList() throws Exception {
        List entities = Collections.emptyList();
        when(provider.all()).thenReturn(entities);
        MainPageContext result = (MainPageContext) page.pageContextForRequest(request);
        assertThat(result.getEntities(), is(sameInstance(entities)));
    }

    @Test
    public void testShouldCreateContextWithPropertyList() throws Exception {
        DefaultPageContext result = page.pageContextForRequest(request);
        assertThat(result.getProperties(), is(page.properties()));
    }

    @Test
    public void testShouldCreateContextWithIdProperyName() throws Exception {
        DefaultPageContext result = page.pageContextForRequest(request);
        assertThat(result.getIdPropertyName(), is(page.idPropertyName()));
    }

    @Test
    public void testShouldCreateContextWithUrl() throws Exception {
        DefaultPageContext result = page.pageContextForRequest(request);
        assertThat(result.getIdPropertyName(), is(page.idPropertyName()));
    }

    @Test
    public void testShouldCreateMainPageContext() throws Exception {
        DefaultPageContext result = page.pageContextForRequest(request);
        assertThat(result, is(instanceOf(MainPageContext.class)));
    }

    @Test
    public void testShouldCreateInstanceOfEditPageContext() throws Exception {
        when(request.entityId()).thenReturn(Optional.of("1"));
        DefaultPageContext result = page.pageContextForRequest(request);
        assertThat(result, is(instanceOf(EditPageContext.class)));

    }
}
