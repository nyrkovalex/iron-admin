package org.github.nyrkovalex.ironadmin.core.defaults;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class DefaultAdminContextTest {

    private DefaultAdminContext context;

    @Before
    public void setUp() throws Exception {
        context = DefaultAdminContext.instance();
    }


    @Test
    public void testShouldBeASingleton() throws Exception {
        DefaultAdminContext context2 = DefaultAdminContext.instance();
        assertThat(context, sameInstance(context2));
    }

    @Test
    public void testShouldSetValidTitle() throws Exception {
        context.setTitle("New title");
        assertThat(context.title(), is("New title"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenSettingNullTitle() throws Exception {
        context.setTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenSettingEmptyTitle() throws Exception {
        context.setTitle("");
    }
}
