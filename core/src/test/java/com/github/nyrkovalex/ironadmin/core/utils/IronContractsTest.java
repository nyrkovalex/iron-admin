package com.github.nyrkovalex.ironadmin.core.utils;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

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
        java.lang.Object[] test = null;
        IronContracts.notNullOrEmpty(test, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyArray() throws Exception {
        Object[] test = new Object[]{ };
        IronContracts.notNullOrEmpty(test, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullFirstObjectArgument() throws Exception {
        IronContracts.notNull(null, "test", new Object(), "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullSecondObjectArgument() throws Exception {
        IronContracts.notNull(new Object(), "test", null, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullFirstStringArgument() throws Exception {
        IronContracts.notNullOrEmpty(null, "test", "stuff", "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullSecondStringArgument() throws Exception {
        IronContracts.notNull("stuff", "test", null, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyFirstStringArgument() throws Exception {
        IronContracts.notNullOrEmpty("", "test", "stuff", "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptySecondStringArgument() throws Exception {
        IronContracts.notNullOrEmpty("stuff", "test", "", "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyCollection() throws Exception {
        Collection test = Collections.emptyList();
        IronContracts.notNullOrEmpty(test, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullCollection() throws Exception {
        Collection test = null;
        IronContracts.notNull(test, "test", new Object(), "test");
    }

}
