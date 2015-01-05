package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironadmin.core.SampleBean;
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
    private EntityProvider<SampleBean, Object> entityProvider;

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

        entityProvider = new EntityProvider<SampleBean, Object>() {
            @Override
            public List<SampleBean> all() {
                return Collections.emptyList();
            }

            @Override
            public SampleBean byId(Object o) {
                return new SampleBean();
            }
        };
        Page<SampleBean, Object> page = createAbstractPage(SampleBean.class, entityProvider, meta, entityMeta);
        properties = page.getProperties();
    }

    private AbstractPage<SampleBean, Object> createAbstractPage(Class<SampleBean> clazz,
                                                                EntityProvider<SampleBean, Object> entityProvider,
                                                                final PageMapping meta,
                                                                final EntityMeta entityMeta) {
        return new AbstractPage<SampleBean, Object>(clazz, entityProvider, meta, entityMeta) {
            @NotNull
            @Override
            public String getTemplateName() {
                return "test";
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
