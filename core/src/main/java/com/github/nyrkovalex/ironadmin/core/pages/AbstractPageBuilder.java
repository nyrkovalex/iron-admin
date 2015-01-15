package com.github.nyrkovalex.ironadmin.core.pages;

import com.github.nyrkovalex.ironadmin.core.EntityProvider;

import java.util.Arrays;

public abstract class AbstractPageBuilder<T> implements PageBuilder<T> {
    private final Class<T> entityClass;
    private final EntityMeta.Builder entityMetaBuilder;
    private PageMapping url;
    private EntityProvider<T> entityProvider;

    protected AbstractPageBuilder(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityMetaBuilder = EntityMeta.of(entityClass);
        this.url = PageMapping.of(entityClass);
    }

    @Override
    public AbstractPageBuilder<T> titled(String title) {
        entityMetaBuilder.title(title);
        return this;
    }

    @Override
    public AbstractPageBuilder<T> putAt(String url) {
        this.url = PageMapping.of(url);
        return this;
    }

    @Override
    public AbstractPageBuilder<T> override(PropertyDefinition... overrides) {
        entityMetaBuilder.overrides(Arrays.asList(overrides));
        return this;
    }

    @Override
    public AbstractPageBuilder<T> backedBy(EntityProvider<T> entityProvider) {
        this.entityProvider = entityProvider;
        return this;
    }

    @Override
    public PageBuilder<T> skip(String... propertyNames) {
        entityMetaBuilder.skips(Arrays.asList(propertyNames));
        return this;
    }

    protected Class<T> entityClass() {
        return entityClass;
    }

    protected EntityMeta entityMeta() {
        return entityMetaBuilder.build();
    }

    protected PageMapping url() {
        return url;
    }

    protected EntityProvider<T> entityProvider() {
        return entityProvider;
    }

}
