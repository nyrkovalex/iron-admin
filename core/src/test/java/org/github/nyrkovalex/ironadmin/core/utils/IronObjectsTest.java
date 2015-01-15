package org.github.nyrkovalex.ironadmin.core.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IronObjectsTest {

    private List<String> names;

    @Before
    public void setUp() throws Exception {
        names = IronObjects.getPropertyNames(SampleBean.class);
    }

    @Test
    public void testShouldGetAllPropertyNames() throws Exception {
        assertThat(names.size(), is(3));
    }

    @Test
    public void testShouldIncludeAllProperties() throws Exception {
        assertThat(names.contains("firstName"), is(true));
        assertThat(names.contains("lastName"), is(true));
        assertThat(names.contains("age"), is(true));
    }

}
