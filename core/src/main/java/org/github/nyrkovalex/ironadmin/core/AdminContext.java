package org.github.nyrkovalex.ironadmin.core;

import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.jetbrains.annotations.NotNull;

public interface AdminContext {
    @NotNull
    PageRegistry pageRegistry();

    @NotNull
    TemplateResolver templateResolver();

    void setTitle(@NotNull String title);

    @NotNull
    String title();
}
