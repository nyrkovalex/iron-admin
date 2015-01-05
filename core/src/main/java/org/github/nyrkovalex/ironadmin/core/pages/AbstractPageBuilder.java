package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;

import java.util.Arrays;

public abstract class AbstractPageBuilder<T, ID> implements PageBuilder<T, ID> {
    private final Class<T> entityClass;
    private final EntityMeta.Builder entityMetaBuilder;
    private PageMapping url;
    private EntityProvider<T, ID> entityProvider;

    protected AbstractPageBuilder(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityMetaBuilder = EntityMeta.of(entityClass);
        this.url = PageMapping.of(entityClass);
    }

    @Override
    public AbstractPageBuilder<T, ID> titled(String title) {
        entityMetaBuilder.title(title);
        return this;
    }

    @Override
    public AbstractPageBuilder<T, ID> putAt(String url) {
        this.url = PageMapping.of(url);
        return this;
    }

    @Override
    public AbstractPageBuilder<T, ID> override(PropertyDefinition... overrides) {
        entityMetaBuilder.overrides(Arrays.asList(overrides));
        return this;
    }

    @Override
    public AbstractPageBuilder<T, ID> backedBy(EntityProvider<T, ID> entityProvider) {
        this.entityProvider = entityProvider;
        return this;
    }

    @Override
    public PageBuilder<T, ID> skip(String... propertyNames) {
        entityMetaBuilder.skips(Arrays.asList(propertyNames));
        return this;
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    protected EntityMeta getEntityMeta() {
        return entityMetaBuilder.build();
    }

    protected PageMapping getUrl() {
        return url;
    }

    protected EntityProvider<T, ID> getEntityProvider() {
        return entityProvider;
    }

}
