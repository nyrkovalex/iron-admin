package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironadmin.core.pages.*;
import org.jetbrains.annotations.NotNull;

public class DefaultTemplatePage<T> extends AbstractPage<T> implements Page<T> {

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T> entityProvider) {
        super(entityClass, entityProvider);
    }

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T> entityProvider,
                                  @NotNull PageMapping pageMapping) {
        super(entityClass, entityProvider, pageMapping);
    }

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T> entityProvider,
                                  @NotNull EntityMeta meta) {
        super(entityClass, entityProvider, meta);
    }

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T> entityProvider,
                                  @NotNull PageMapping url,
                                  @NotNull EntityMeta entityMeta) {
        super(entityClass, entityProvider, url, entityMeta);
    }

    public static <E> PageBuilder<E> of(Class<E> entityClass) {
        return new DefaultPageBuilder<>(entityClass);
    }

    @NotNull
    @Override
    public DefaultPageContext pageContextForRequest(PageRequest request) {
        if (request.entityId().isPresent()) {
            return new EditPageContext<>(
                    provider().one(request.entityId().get()),
                    title(),
                    properties(),
                    idPropertyName(),
                    url());
        }
        return new MainPageContext<>(
                provider().all(),
                title(),
                properties(),
                idPropertyName(),
                url());
    }

    public static class DefaultPageBuilder<E> extends AbstractPageBuilder<E> {

        public DefaultPageBuilder(Class<E> entityClass) {
            super(entityClass);
        }

        @Override
        public Page<E> build() {
            return new DefaultTemplatePage<>(entityClass(), entityProvider(), url(), entityMeta());
        }
    }

}
