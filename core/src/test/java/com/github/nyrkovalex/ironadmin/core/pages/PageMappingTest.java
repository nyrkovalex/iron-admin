package com.github.nyrkovalex.ironadmin.core.pages;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class PageMappingTest {

    private PageMapping pageMapping;

    @Before
    public void setUp() throws Exception {
        pageMapping = PageMapping.of(PageMappingTest.class);
    }

    @Test
    public void testShouldUseClassNameAsPageUrl() throws Exception {
        assertThat(pageMapping.getUrl(), is("/page-mapping-tests"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIfUrlIsNull() throws Exception {
        String url = null;
        PageMapping.of(url);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIfClassIsNull() throws Exception {
        Class<?> clazz = null;
        PageMapping.of(clazz);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenConstructingWithEmptyUrl() throws Exception {
        PageMapping.of("");
    }

}
