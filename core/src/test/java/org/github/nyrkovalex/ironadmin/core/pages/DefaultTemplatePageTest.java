package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.SampleBean;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultTemplatePageTest {

    private List<PropertyDefinition> properties;

    @Before
    public void setUp() throws Exception {
        DefaultTemplatePage<SampleBean> page = new DefaultTemplatePage<>("Test", "/test", SampleBean.class, null);
        page.overridePropertyDefinitions(new PropertyDefinition("age", "FooBar"),
                new PropertyDefinition("firstName", "Dude"));
        properties = page.getProperties();
    }

    @Test
    public void testShouldGetPropertyDescriptors() throws Exception {
        assertThat(properties.size(), is(3));
    }

    @Test
    public void testShouldGetSortedProperties() throws Exception {
        assertThat(properties.get(0).getName(), is("age"));
        assertThat(properties.get(1).getName(), is("firstName"));
        assertThat(properties.get(2).getName(), is("lastName"));
    }

    @Test
    public void testShouldOverridePropertyDefinitions() throws Exception {
        assertThat(properties.get(0).getDisplayName(), is("FooBar"));
        assertThat(properties.get(1).getDisplayName(), is("Dude"));
    }
}
