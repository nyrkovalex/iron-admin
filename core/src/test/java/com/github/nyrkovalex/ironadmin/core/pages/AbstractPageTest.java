package com.github.nyrkovalex.ironadmin.core.pages;

import com.github.nyrkovalex.ironadmin.core.EntityProvider;
import com.github.nyrkovalex.ironadmin.core.SampleBean;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AbstractPageTest {

    private List<PropertyDefinition> properties;
    private EntityProvider<SampleBean> entityProvider;

    @Before
    public void setUp() throws Exception {
        PageMapping meta = PageMapping.of("/test");
        EntityMeta entityMeta = EntityMeta.of(SampleBean.class)
                .title("Test")
                .overrides(Arrays.asList(
                        new PropertyDefinition("age", "FooBar"),
                        new PropertyDefinition("firstName", "Dude")))
                .skips(Arrays.asList("secret"))
                .build();

        entityProvider = new EntityProvider<SampleBean>() {
            @Override
            public List<SampleBean> all() {
                return Collections.emptyList();
            }

            @Override
            public SampleBean one(String o) {
                return new SampleBean();
            }
        };
        Page<SampleBean> page = createAbstractPage(SampleBean.class, entityProvider, meta, entityMeta);
        properties = page.properties();
    }

    private AbstractPage<SampleBean> createAbstractPage(
            Class<SampleBean> clazz,
            EntityProvider<SampleBean> entityProvider,
            final PageMapping meta,
            final EntityMeta entityMeta) {
        return new AbstractPage<SampleBean>(clazz, entityProvider, meta, entityMeta) {
            @NotNull
            @Override
            public PageContext pageContextForRequest(PageRequest request) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Test
    public void testShouldGetCorrectCountOfPropertyDescriptors() throws Exception {
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

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenNullMetaProvided() throws Exception {
        createAbstractPage(SampleBean.class, entityProvider, null, EntityMeta.of(SampleBean.class).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenNullSchemaProvided() throws Exception {
        createAbstractPage(SampleBean.class, entityProvider, PageMapping.of("/url"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenNullEntityProviderProvided() throws Exception {
        createAbstractPage(SampleBean.class, null, PageMapping.of("/url"), EntityMeta.of(SampleBean.class).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenNullENtityClassProvided() throws Exception {
        createAbstractPage(null, entityProvider, PageMapping.of("/url"), EntityMeta.of(SampleBean.class).build());
    }
}
