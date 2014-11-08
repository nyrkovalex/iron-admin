package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.jetbrains.annotations.NotNull;

public class DefaultTemplatePage<T> extends AbstractPage<T> implements Page<T> {
    public static final String DEFAULT_TEMPLATE_NAME = "default";

    public DefaultTemplatePage(@NotNull Class<T> entityClass, @NotNull EntityProvider<T> entityProvider) {
        super(entityClass, entityProvider);
    }

    public DefaultTemplatePage(@NotNull Class<T> entityClass, @NotNull EntityProvider<T> entityProvider, @NotNull PageUrl pageUrl) {
        super(entityClass, entityProvider, pageUrl);
    }

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T> entityProvider,
                                  @NotNull PageUrl meta,
                                  @NotNull EntityMeta entityMeta) {
        super(entityClass, entityProvider, meta, entityMeta);
    }

    @NotNull
    @Override
    public final String getTemplateName() {
        return DEFAULT_TEMPLATE_NAME;
    }

    public static <E> PageBuilder<E> createFor(Class<E> entityClass) {
        return new DefaultPageBuilder<>(entityClass);
    }

    public static class DefaultPageBuilder<E> extends AbstractPageBuilder<E> {

        public DefaultPageBuilder(Class<E> entityClass) {
            super(entityClass);
        }

        @Override
        public Page<E> build() {
            return new DefaultTemplatePage<>(
                    getEntityClass(), getEntityProvider(), getUrl(), getEntityMeta()
            );
        }
    }
}
