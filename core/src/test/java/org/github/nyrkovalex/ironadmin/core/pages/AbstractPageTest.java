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
  private EntityProvider<SampleBean> entityProvider;

  @Before
  public void setUp() throws Exception {
    PageUrl meta = PageUrl.of("/test");
    EntityMeta entityMeta = EntityMeta.of("Test", Arrays.asList(
        new PropertyDefinition("age", "FooBar"),
        new PropertyDefinition("firstName", "Dude")
    ), Arrays.asList(
        "secret"
    ));
    entityProvider = new EntityProvider<SampleBean>() {
      @Override
      public List<SampleBean> all() {
        return Collections.emptyList();
      }
    };
    Page<SampleBean> page = createAbstractPage(SampleBean.class, entityProvider, meta, entityMeta);
    properties = page.getProperties();
  }

  private AbstractPage<SampleBean> createAbstractPage(Class<SampleBean> clazz, EntityProvider<SampleBean> entityProvider, final PageUrl meta, final EntityMeta entityMeta) {
    return new AbstractPage<SampleBean>(clazz, entityProvider, meta, entityMeta) {
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
    createAbstractPage(SampleBean.class, entityProvider, null, EntityMeta.of(SampleBean.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowWhenNullSchemaProvided() throws Exception {
    createAbstractPage(SampleBean.class, entityProvider, PageUrl.of("/url"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowWhenNullEntityProviderProvided() throws Exception {
    createAbstractPage(SampleBean.class, null, PageUrl.of("/url"), EntityMeta.of(SampleBean.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowWhenNullENtityClassProvided() throws Exception {
    createAbstractPage(null, entityProvider, PageUrl.of("/url"), EntityMeta.of(SampleBean.class));
  }
}
