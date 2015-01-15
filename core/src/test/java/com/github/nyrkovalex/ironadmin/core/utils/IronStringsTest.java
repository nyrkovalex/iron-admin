package com.github.nyrkovalex.ironadmin.core.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
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

    @Test
    public void testShouldConvertCapitalFirst() throws Exception {
        assertThat(IronStrings.camelCaseToSentence("SomeThing"), is("Some Thing"));
    }

    @Test
    public void testShouldConvertOneLetterWord() throws Exception {
        assertThat(IronStrings.camelCaseToSentence("a"), is("A"));
    }

    @Test
    public void testShouldSplitCamelCaseToThreeWords() throws Exception {
        Collection<String> words = IronStrings.splitCamelCaseToWords("SomeLongThing");
        assertThat(words.size(), is(3));
    }

    @Test
    public void testShouldReturnAllWordsAsLowercase() throws Exception {
        List<String> words = new ArrayList<>(IronStrings.splitCamelCaseToWords("SomeLongThing"));
        assertThat(words.get(0), is("some"));
        assertThat(words.get(1), is("long"));
        assertThat(words.get(2), is("thing"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnNullString() throws Exception {
        IronStrings.splitCamelCaseToWords(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowOnEmptyString() throws Exception {
        IronStrings.splitCamelCaseToWords("");
    }
}
