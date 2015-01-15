package com.github.nyrkovalex.ironadmin.core;

import com.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
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
