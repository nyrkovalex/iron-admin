package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.utils.IronContracts;
import org.github.nyrkovalex.ironadmin.core.utils.IronStrings;
import org.jetbrains.annotations.NotNull;

public class PageMapping {
    private final String url;

    private PageMapping(String url) {
        this.url = url;
    }

    private static String fromClassName(String name) {
        return "/" + IronStrings.splitCamelCaseToWords(name).stream()
                .reduce((left, right) -> left + "-" + right)
                .orElseThrow(AssertionError::new) + "s";
    }

    public static PageMapping of(@NotNull Class<?> entityClass) {
        IronContracts.notNull(entityClass, "class");

        return new PageMapping(fromClassName(entityClass.getSimpleName()));
    }

    public String getUrl() {
        return url;
    }

    public static PageMapping of(@NotNull String url) {
        IronContracts.notNullOrEmpty(url, "url");

        return new PageMapping(url);
    }
}
