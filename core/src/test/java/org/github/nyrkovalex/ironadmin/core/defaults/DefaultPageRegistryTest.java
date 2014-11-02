package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.dummy.DummyPage;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class DefaultPageRegistryTest {

    private DefaultPageRegistry registry;
    private DummyPage dummyPage;

    @Before
    public void setUp() throws Exception {
        registry = new DefaultPageRegistry();
        dummyPage = new DummyPage();
    }

    @Test
    public void testShouldRegisterPage() throws Exception {
        registry.register(dummyPage);
        assertThat(registry.getPage(dummyPage.getUrl()).get(), sameInstance(dummyPage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowWhenRegisteringNullPage() throws Exception {
        Page page = null;
        registry.register(page);
    }

    @Test
    public void testShouldReturnItself() throws Exception {
        assertThat(registry.register(dummyPage), sameInstance(registry));
    }

    @Test
    public void testShouldRegisterMultiplePages() throws Exception {
        Page secondPage = new DummyPage("/test2");
        registry.register(dummyPage, secondPage);
        assertThat(registry.getPage(dummyPage.getUrl()).get(), sameInstance(dummyPage));
        assertThat(registry.getPage(secondPage.getUrl()).get(), sameInstance(secondPage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldNotPuttDuplicatePages() throws Exception {
        Page secondPage = new DummyPage(dummyPage.getUrl());
        registry.register(dummyPage);
        registry.register(secondPage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyRegisterInvokation() throws Exception {
        registry.register();
    }

    @Test
    public void testShouldReturnPagesInTheOrderOfRegistration() throws Exception {
        Page page2 = new DummyPage("/2");
        Page page3 = new DummyPage("/3");
        Page page4 = new DummyPage("/4");

        registry.register(dummyPage, page2, page3, page4);
        List<Page> pages = new ArrayList<>(registry.getPages());

        assertThat(pages.get(0), is(dummyPage));
        assertThat(pages.get(1), is(page2));
        assertThat(pages.get(2), is(page3));
        assertThat(pages.get(3), is(page4));
    }
}
