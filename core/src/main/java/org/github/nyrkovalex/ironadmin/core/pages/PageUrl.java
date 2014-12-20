package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironutils.IronContracts;
import org.github.nyrkovalex.ironutils.IronStrings;
import org.jetbrains.annotations.NotNull;

public class PageUrl {
  private final String url;

  private PageUrl(String url) {
    this.url = url;
  }

  private static String urlFromClassName(String name) {
    return "/" + IronStrings.splitCamelCaseToWords(name).stream()
        .reduce((left, right) -> left + "-" + right)
        .orElseThrow(AssertionError::new) + "s";
  }

  public static PageUrl of(@NotNull Class<?> entityClass) {
    IronContracts.notNull(entityClass, "class");

    return new PageUrl(urlFromClassName(entityClass.getSimpleName()));
  }

  public String getUrl() {
    return url;
  }

  public static PageUrl of(@NotNull String url) {
    IronContracts.notNullOrEmpty(url, "url");

    return new PageUrl(url);
  }
}
