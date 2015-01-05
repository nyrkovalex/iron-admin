package org.github.nyrkovalex.ironadmin.core.pages.dummy;

import org.github.nyrkovalex.ironadmin.core.AdminContext;
import org.github.nyrkovalex.ironadmin.core.TemplateResolver;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.jetbrains.annotations.NotNull;

public class DummyAdminContext implements AdminContext {
    @NotNull
    @Override
    public PageRegistry pageRegistry() {
        return DummyFactory.getRegistry();
    }

    @NotNull
    @Override
    public TemplateResolver templateResolver() {
        return DummyFactory.getResolver();
    }

    @Override
    public void setTitle(@NotNull String title) {

    }

    @NotNull
    @Override
    public String title() {
        return "TEST";
    }
}
