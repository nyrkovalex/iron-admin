package com.github.nyrkovalex.ironadmin.core.defaults;

import com.github.nyrkovalex.ironadmin.core.AdminContext;
import com.github.nyrkovalex.ironadmin.core.TemplateResolver;
import com.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.jetbrains.annotations.NotNull;

public class DefaultAdminContext implements AdminContext {
    private static final DefaultAdminContext INSTANCE = new DefaultAdminContext();

    private final DefaultPageRegistry pageRegistry;
    private final ThymeleafTemplateResolver templateResolver;
    private final EnvironmentContext env;

    private DefaultAdminContext() {
        pageRegistry = new DefaultPageRegistry();
        env = new EnvironmentContext(pageRegistry);
        templateResolver = new ThymeleafTemplateResolver(env);
    }

    @NotNull
    @Override
    public PageRegistry pageRegistry() {
        return pageRegistry;
    }

    @NotNull
    @Override
    public TemplateResolver templateResolver() {
        return templateResolver;
    }

    @Override
    public void setTitle(@NotNull String title) {
        env.setTitle(title);
    }

    @NotNull
    @Override
    public String title() {
        return env.getTitle();
    }

    public static DefaultAdminContext instance() {
        return INSTANCE;
    }
}
