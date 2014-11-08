package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.SampleBean;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class EntityMetaTest {

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIfClassIsNull() throws Exception {
        EntityMeta.of(null);
    }

    @Test
    public void testShouldUseClassNameAsPageTitle() throws Exception {
        EntityMeta meta = EntityMeta.of(SampleBean.class);
        assertThat(meta.getTitle(), is("Sample Bean"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIfTitleIsNull() throws Exception {
        String title = null;
        EntityMeta.of(title, Arrays.asList(
                new PropertyDefinition("test")
        ), Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIsSkippedAreNull() throws Exception {
        Collection<String> skipped = null;
        EntityMeta.of(SampleBean.class, skipped);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIfTitleIsEmpty() throws Exception {
        EntityMeta.of("", Arrays.asList(
                new PropertyDefinition("test")
        ), Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIfDefinitionsAreNull() throws Exception {
        EntityMeta.of("title", null, Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowIfSkippedPropertiesAreNull() throws Exception {
        EntityMeta.of("title", Arrays.asList(
                new PropertyDefinition("test")
        ), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenCreatingFromNullClass() throws Exception {
        EntityMeta.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenCreatingFromOptionalsWithNullTitle() throws Exception {
        Optional<String> title = null;
        Optional<Collection<PropertyDefinition>> overrides = Optional.empty();
        Optional<Collection<String>> skipped = Optional.empty();
        EntityMeta.of(SampleBean.class, title, overrides, skipped);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenCreatingFromOptionalsWithNullOverrides() throws Exception {
        Optional<String> title = Optional.empty();
        Optional<Collection<PropertyDefinition>> overrides = null;
        Optional<Collection<String>> skipped = Optional.empty();
        EntityMeta.of(SampleBean.class, title, overrides, skipped);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenCreatingFromOptionalsWithNullSkipped() throws Exception {
        Optional<String> title = Optional.empty();
        Optional<Collection<PropertyDefinition>> overrides = Optional.empty();
        Optional<Collection<String>> skipped = null;
        EntityMeta.of(SampleBean.class, title, overrides, skipped);
    }


    @Test
    public void testShouldSkipProperty() throws Exception {
        EntityMeta meta = EntityMeta.of(SampleBean.class, Arrays.asList("secret"));
        assertThat(meta.getSkippedProperties().size(), is(1));
    }
}
