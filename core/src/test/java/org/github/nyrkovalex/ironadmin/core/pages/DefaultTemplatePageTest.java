package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.SampleBean;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultTemplatePageTest {

    private DefaultTemplatePage<SampleBean> page;
    private List<PropertyDefinition> properties;

    @Before
    public void setUp() throws Exception {
        page = new DefaultTemplatePage<SampleBean>("Test", "/test", SampleBean.class) {
            @NotNull
            @Override
            public Collection<SampleBean> getEntities() {
                return Collections.emptyList();
            }
        };
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
