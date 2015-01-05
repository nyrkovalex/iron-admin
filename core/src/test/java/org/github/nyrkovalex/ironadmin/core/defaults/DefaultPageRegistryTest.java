package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.dummy.DummyPage;
import org.junit.Before;
import org.junit.Test;

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
        assertThat(registry.pageFor(dummyPage.url()).get(), sameInstance(dummyPage));
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
        assertThat(registry.pageFor(dummyPage.url()).get(), sameInstance(dummyPage));
        assertThat(registry.pageFor(secondPage.url()).get(), sameInstance(secondPage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldNotPuttDuplicatePages() throws Exception {
        Page secondPage = new DummyPage(dummyPage.url());
        registry.register(dummyPage);
        registry.register(secondPage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyRegisterInvokation() throws Exception {
        registry.register();
    }
}
