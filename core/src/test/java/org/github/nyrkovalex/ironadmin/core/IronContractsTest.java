package org.github.nyrkovalex.ironadmin.core;

import org.junit.Test;

@SuppressWarnings("ConstantConditions")
public class IronContractsTest {
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullValue() throws Exception {
        IronContracts.notNull(null, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullStringValue() throws Exception {
        String test = null;
        IronContracts.notNullOrEmpty(test, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyStringValue() throws Exception {
        IronContracts.notNullOrEmpty("", "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullArray() throws Exception {
        Object[] test = null;
        IronContracts.notNullOrEmpty(test, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyArray() throws Exception {
        Object[] test = new Object[] { };
        IronContracts.notNullOrEmpty(test, "test");
    }
}
