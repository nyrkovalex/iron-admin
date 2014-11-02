package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;

public abstract class AbstractPageBuilder<E> {
    private final Class<E> entityClass;
    private String title;
    private String url;
    private PropertyDefinition[] overrides;
    private EntityProvider<E> entityProvider;

    public AbstractPageBuilder(Class<E> entityClass) {
        this.entityClass = entityClass;
        overrides = new PropertyDefinition[0];
    }

    public AbstractPageBuilder<E> titled(String title) {
        this.title = title;
        return this;
    }

    public AbstractPageBuilder<E> putAt(String url) {
        this.url = url;
        return this;
    }

    public AbstractPageBuilder<E> withOverrides(PropertyDefinition... overrides) {
        this.overrides = overrides;
        return this;
    }

    public AbstractPageBuilder<E> backedBy(EntityProvider<E> entityProvider) {
        this.entityProvider = entityProvider;
        return this;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public PropertyDefinition[] getOverrides() {
        return overrides;
    }

    public EntityProvider<E> getEntityProvider() {
        return entityProvider;
    }

    public abstract Page<E> build();
}
