package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.github.nyrkovalex.ironutils.IronContracts;

import java.util.Map;

class EnvironmentContext {
    public static final String DEFAULT_TITLE = "Iron Admin";
    private final PageRegistry pageRegistry;
    private String title;

    public EnvironmentContext(PageRegistry pageRegistry) {
        this.pageRegistry = pageRegistry;
        setTitle(DEFAULT_TITLE);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        IronContracts.notNullOrEmpty(title, "title");
        this.title = title;
    }

    public Map<String, String> getPages() {
        return pageRegistry.pageMap();
    }
}