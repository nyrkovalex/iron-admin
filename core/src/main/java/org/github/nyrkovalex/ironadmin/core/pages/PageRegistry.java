package org.github.nyrkovalex.ironadmin.core.pages;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public interface PageRegistry {
    /**
     * Retrieves a pages from register by its url
     *
     * @param url url of target pages
     * @return optional-wrapped pages or null if pages was not found
     */
    @NotNull
    Optional<Page> getPage(String url);

    /**
     * Registers a pages within admin application
     *
     * @param page pages to register
     * @return itself for chained invocations
     */
    @NotNull
    PageRegistry register(@NotNull Page page);

    /**
     * Registers multiple pages within admin application
     *
     * @param pages pages to register
     * @return itself for chained invocations
     */
    @NotNull
    PageRegistry register(@NotNull Page<?, ?>... pages);

    /**
     * Returns all pages registered within current registry
     *
     * @return collection of pages registered within current registry
     */
    @NotNull
    Collection<Page> getPages();
}
