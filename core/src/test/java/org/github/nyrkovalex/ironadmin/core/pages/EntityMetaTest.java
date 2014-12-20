package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.SampleBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class EntityMetaTest {

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowIfClassIsNull() throws Exception {
    Class<?> clazz = null;
    EntityMeta.of(clazz);
  }

  @Test
  public void testShouldUseClassNameAsPageTitle() throws Exception {
    EntityMeta meta = EntityMeta.of(SampleBean.class).build();
    assertThat(meta.getTitle(), is("Sample Bean"));
  }

  @Test
  public void testShoudCreateEntityMetaWithEmptyOverrides() throws Exception {
    EntityMeta meta = EntityMeta.of(SampleBean.class).build();
    assertThat(meta.getOverrides(), is(Collections.emptyList()));
  }

  @Test
  public void testShouldCreateEntityMetaWithEmptySkips() throws Exception {
    EntityMeta meta = EntityMeta.of(SampleBean.class).build();
    assertThat(meta.getSkippedProperties(), is(Collections.emptyList()));
  }

  @Test
  public void testShouldCreateEntityMetaWithDefaultIdPoperty() throws Exception {
    EntityMeta meta = EntityMeta.of(SampleBean.class).build();
    assertThat(meta.getIdPropertyName(), is(EntityMeta.DEFAULT_ID_PROPERTY_NAME));
  }

  @Test
  public void testShouldSetOverrides() throws Exception {
    Collection<PropertyDefinition> overrides = new ArrayList<>();
    EntityMeta meta = EntityMeta.of(SampleBean.class)
        .overrides(overrides)
        .build();
    assertThat(meta.getOverrides(), is(overrides));
  }

  @Test
  public void testShouldMakeADefensiveCopyOfOverrides() throws Exception {
    Collection<PropertyDefinition> overrides = new ArrayList<>();
    EntityMeta meta = EntityMeta.of(SampleBean.class)
        .overrides(overrides)
        .build();
    assertThat(meta.getOverrides(), is(not(sameInstance(overrides))));
  }

  @Test
  public void testShouldSetSkips() throws Exception {
    Collection<String> skips = new ArrayList<>();
    EntityMeta meta = EntityMeta.of(SampleBean.class)
        .skips(skips)
        .build();
    assertThat(meta.getSkippedProperties(), is(skips));
  }

  @Test
  public void testShouldMakeADefensiveCopyOfSkips() throws Exception {
    Collection<String> skips = new ArrayList<>();
    EntityMeta meta = EntityMeta.of(SampleBean.class)
        .skips(skips)
        .build();
    assertThat(meta.getSkippedProperties(), is(not(sameInstance(skips))));
  }

  @Test
  public void testShouldSetIdPropertyName() throws Exception {
    EntityMeta meta = EntityMeta.of(SampleBean.class)
        .idPropertyName("code")
        .build();
    assertThat(meta.getIdPropertyName(), is("code"));
  }


  @Test
  public void testShouldSetTitle() throws Exception {
    EntityMeta meta = EntityMeta.of(SampleBean.class).title("Test").build();
    assertThat(meta.getTitle(), is("Test"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowOnNullOverrides() throws Exception {
    Collection<PropertyDefinition> overrides = null;
    EntityMeta.of(SampleBean.class).overrides(overrides);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowOnNullSkips() throws Exception {
    Collection<String> skips = null;
    EntityMeta.of(SampleBean.class).skips(skips);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowOnNullIdPropertyName() throws Exception {
    EntityMeta.of(SampleBean.class).idPropertyName(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowOnEmptyIdPropertyName() throws Exception {
    EntityMeta.of(SampleBean.class).idPropertyName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowOnNullTitle() throws Exception {
    EntityMeta.of(SampleBean.class).title(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowOnEmptyTitle() throws Exception {
    EntityMeta.of(SampleBean.class).title("");
  }
}
