package org.github.nyrkovalex.ironadmin.core;

import org.jetbrains.annotations.NotNull;

public final class IronStrings {

    private IronStrings() {

    }

    @NotNull
    public static String camelCaseToSentence(@NotNull String str) {
        IronContracts.notNullOrEmpty(str, "name");

        StringBuilder words = new StringBuilder();
        String[] letters = str.split("");
        int wordStart = 0;
        int i;
        for (i = 0; i < letters.length; i++) {
            String currentLetter = letters[i];
            boolean isUpperCase = currentLetter.toUpperCase().equals(currentLetter);
            if (isUpperCase) {
                String currentWord = str.substring(wordStart, i);
                words.append(capitalizeFirstLetter(currentWord)).append(" ");
                wordStart = i;
            }
        }
        String lastWord = str.substring(wordStart, i);
        words.append(capitalizeFirstLetter(lastWord));
        return words.toString();
    }

    @NotNull
    public static String capitalizeFirstLetter(@NotNull String str) {
        IronContracts.notNullOrEmpty(str, "name");

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
