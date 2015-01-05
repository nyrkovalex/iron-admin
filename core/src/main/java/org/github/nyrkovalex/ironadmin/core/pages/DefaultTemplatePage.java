package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.jetbrains.annotations.NotNull;

public class DefaultTemplatePage<T, ID> extends AbstractPage<T, ID> implements Page<T, ID> {
    public static final String DEFAULT_TEMPLATE_NAME = "index";

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T, ID> entityProvider) {
        super(entityClass, entityProvider);
    }

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T, ID> entityProvider,
                                  @NotNull PageMapping pageMapping) {
        super(entityClass, entityProvider, pageMapping);
    }

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T, ID> entityProvider,
                                  @NotNull EntityMeta meta) {
        super(entityClass, entityProvider, meta);
    }

    protected DefaultTemplatePage(@NotNull Class<T> entityClass,
                                  @NotNull EntityProvider<T, ID> entityProvider,
                                  @NotNull PageMapping url,
                                  @NotNull EntityMeta entityMeta) {
        super(entityClass, entityProvider, url, entityMeta);
    }

    @NotNull
    @Override
    public final String getTemplateName() {
        return DEFAULT_TEMPLATE_NAME;
    }

    public static <E, K> PageBuilder<E, K> of(Class<E> entityClass) {
        return new DefaultPageBuilder<>(entityClass);
    }

    public static class DefaultPageBuilder<E, K> extends AbstractPageBuilder<E, K> {

        public DefaultPageBuilder(Class<E> entityClass) {
            super(entityClass);
        }

        @Override
        public Page<E, K> build() {
            return new DefaultTemplatePage<>(
                    getEntityClass(), getEntityProvider(), getUrl(), getEntityMeta()
            );
        }
    }
}
