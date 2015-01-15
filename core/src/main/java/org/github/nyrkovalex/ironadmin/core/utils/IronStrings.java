package org.github.nyrkovalex.ironadmin.core.utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public final class IronStrings {

    private IronStrings() {

    }

    @NotNull
    public static String camelCaseToSentence(@NotNull String str) {
        IronContracts.notNullOrEmpty(str, "string");

        Collection<String> words = splitCamelCaseToWords(str);
        return words.stream().map(IronStrings::capitalizeFirstLetter).reduce((left, right) ->
                                                                                     left + " " + right
        ).orElseThrow(IllegalStateException::new);
    }

    @NotNull
    public static String capitalizeFirstLetter(@NotNull String str) {
        IronContracts.notNullOrEmpty(str, "name");

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @NotNull
    public static Collection<String> splitCamelCaseToWords(@NotNull String str) {
        IronContracts.notNullOrEmpty(str, "string");

        Collection<String> words = new ArrayList<>();
        String[] letters = str.split("");
        int wordStart = 0;
        int i;
        for (i = 1; i < letters.length; i++) {
            String currentLetter = letters[i];
            boolean isUpperCase = currentLetter.toUpperCase().equals(currentLetter);
            if (isUpperCase) {
                String currentWord = str.substring(wordStart, i);
                words.add(currentWord.toLowerCase());
                wordStart = i;
            }
        }
        String lastWord = str.substring(wordStart, i);
        words.add(lastWord.toLowerCase());
        return words;
    }
}
