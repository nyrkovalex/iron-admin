package org.github.nyrkovalex.ironadmin.core.pages;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class PropertyDefinitionTest {

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullName() throws Exception {
        new PropertyDefinition(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyName() throws Exception {
        new PropertyDefinition("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullDisplayName() throws Exception {
        new PropertyDefinition("test", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyDisplayName() throws Exception {
        new PropertyDefinition("test", "");
    }

    @Test
    public void testShouldCapitalizeOneWordName() throws Exception {
        PropertyDefinition prop = new PropertyDefinition("test");
        assertThat(prop.getDisplayName(), is("Test"));
    }

    @Test
    public void testShouldSplitCamelCaseWords() throws Exception {
        PropertyDefinition prop = new PropertyDefinition("myTestProperty");
        assertThat(prop.getDisplayName(), is("My Test Property"));
    }
}
