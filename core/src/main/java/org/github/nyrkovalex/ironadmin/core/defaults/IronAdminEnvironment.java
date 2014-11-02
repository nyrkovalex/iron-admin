package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.IronContracts;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;

import java.util.Collection;

class IronAdminEnvironment {
    public static final String DEFAULT_TITLE = "Iron Admin";
    private final PageRegistry pageRegistry;
    private String title;

    public IronAdminEnvironment(PageRegistry pageRegistry) {
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

    public Collection<Page> getPages() {
        return pageRegistry.getPages();
    }
}
