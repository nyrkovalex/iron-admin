package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.github.nyrkovalex.ironadmin.core.utils.IronContracts;
import org.github.nyrkovalex.ironadmin.core.utils.IronMaps;
import org.jetbrains.annotations.NotNull;

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
    public Optional<Page> pageFor(String url) {
        return Optional.ofNullable(pages.get(url));
    }

    @NotNull
    @Override
    public PageRegistry register(@NotNull Page page) {
        IronContracts.notNull(page, "page");
        IronMaps.putOrThrowIfPresent(pages, page.url(), page);
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

    @Override
    @NotNull
    public Map<String, String> pageMap() {
        Map<String, String> result = new LinkedHashMap<>(pages.size());
        pages.values().stream().forEach((p) -> result.put(p.url(), p.title()));
        return result;
    }
}
