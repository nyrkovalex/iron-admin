package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.AdminContext;
import org.github.nyrkovalex.ironadmin.core.TemplateResolver;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.jetbrains.annotations.NotNull;

public class DefaultContext implements AdminContext {
    private static DefaultContext instance = new DefaultContext();

    private final DefaultPageRegistry pageRegistry;
    private final ThymeleafTemplateResolver templateResolver;
    private final IronAdminEnvironment env;

    private DefaultContext() {
        pageRegistry = new DefaultPageRegistry();
        env = new IronAdminEnvironment(pageRegistry);
        templateResolver = new ThymeleafTemplateResolver(env);
    }

    @NotNull
    @Override
    public PageRegistry getRegistry() {
        return pageRegistry;
    }

    @NotNull
    public TemplateResolver getTemplateResolver() {
        return templateResolver;
    }

    @Override
    public void setTitle(@NotNull String title) {
        env.setTitle(title);
    }

    @NotNull
    @Override
    public String getTitle() {
        return env.getTitle();
    }

    public static DefaultContext getInstance() {
        return instance;
    }
}
