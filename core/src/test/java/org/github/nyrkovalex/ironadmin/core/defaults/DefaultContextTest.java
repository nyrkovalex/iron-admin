package org.github.nyrkovalex.ironadmin.core.defaults;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class DefaultContextTest {

    private DefaultContext context;

    @Before
    public void setUp() throws Exception {
        context = DefaultContext.getInstance();
    }


    @Test
    public void testShouldBeASingleton() throws Exception {
        DefaultContext context2 = DefaultContext.getInstance();
        assertThat(context, sameInstance(context2));
    }

    @Test
    public void testShouldSetValidTitle() throws Exception {
        context.setTitle("New title");
        assertThat(context.getTitle(), is("New title"));
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
