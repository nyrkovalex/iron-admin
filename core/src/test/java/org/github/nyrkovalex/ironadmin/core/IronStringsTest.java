package org.github.nyrkovalex.ironadmin.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IronStringsTest {
    @Test
    public void testShouldCapitalizeFirstLetter() throws Exception {
        assertThat(IronStrings.capitalizeFirstLetter("test"), is("Test"));
    }

    @Test
    public void testShouldConvertToOneWordSentence() throws Exception {
        assertThat(IronStrings.camelCaseToSentence("foo"), is("Foo"));
    }

    @Test
    public void testShouldConvertToLongSentence() throws Exception {
        assertThat(IronStrings.camelCaseToSentence("myLongSentenceInCamelCase"),
                is("My Long Sentence In Camel Case"));
    }
}
