package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.IronContracts;
import org.github.nyrkovalex.ironadmin.core.IronMaps;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

class DefaultPageRegistry implements PageRegistry {
    private final Map<String, Page> pages;

    DefaultPageRegistry() {
        pages = new LinkedHashMap<>();
    }

    @NotNull
    @Override
    public Optional<Page> getPage(String url) {
        return Optional.ofNullable(pages.get(url));
    }

    @NotNull
    @Override
    public PageRegistry register(@NotNull Page page) {
        IronContracts.notNull(page, "page");
        IronMaps.putOrThrowIfPresent(pages, page.getUrl(), page);
        return this;
    }

    @NotNull
    @Override
    public PageRegistry register(@NotNull Page... pages) {
        IronContracts.notNullOrEmpty(pages, "pages");
        for (Page p : pages) {
            register(p);
        }
        return this;
    }

    @NotNull
    @Override
    public Collection<Page> getPages() {
        return pages.values();
    }
}
