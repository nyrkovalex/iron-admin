package org.github.nyrkovalex.ironadmin.core.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IronMapsTest {

    private HashMap<String, String> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
        map.put("foo", "bar");
    }

    @Test
    public void testShouldPutValueUnderTheNewKey() throws Exception {
        IronMaps.putOrThrowIfPresent(map, "baz", "fuzz");
        assertThat(map.get("baz"), is("fuzz"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnExistingKey() throws Exception {
        IronMaps.putOrThrowIfPresent(map, "foo", "some");
    }
}
