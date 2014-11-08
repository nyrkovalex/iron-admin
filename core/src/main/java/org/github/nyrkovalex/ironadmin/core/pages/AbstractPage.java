package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironutils.IronContracts;
import org.github.nyrkovalex.ironutils.IronObjects;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractPage<T> implements Page<T> {
    private final String title;
    private final String url;
    private final Class<T> entityClass;
    private final EntityProvider<T> entityProvider;
    private final Collection<String> skippedProperties;
    private final Map<String, PropertyDefinition> definitionOverrides;

    protected AbstractPage(@NotNull Class<T> entityClass,
                           @NotNull EntityProvider<T> entityProvider) {
        this(entityClass, entityProvider, PageUrl.of(entityClass), EntityMeta.of(entityClass));
    }


    protected AbstractPage(@NotNull Class<T> entityClass,
                           @NotNull EntityProvider<T> entityProvider,
                           @NotNull PageUrl pageUrl) {
        this(entityClass, entityProvider, pageUrl, EntityMeta.of(entityClass));
    }

    protected AbstractPage(@NotNull Class<T> entityClass,
                           @NotNull EntityProvider<T> entityProvider,
                           @NotNull PageUrl pageUrl,
                           @NotNull EntityMeta entityMeta) {
        validateContracts(entityClass, entityProvider, pageUrl, entityMeta);

        this.title = entityMeta.getTitle();
        this.url = pageUrl.getUrl();
        this.entityClass = entityClass;
        this.entityProvider = entityProvider;
        this.skippedProperties = entityMeta.getSkippedProperties();
        this.definitionOverrides = buildOverridesMap(entityMeta.getOverrides());
    }

    private static void validateContracts(Class<?> entityClass,
                                          EntityProvider<?> entityProvider,
                                          PageUrl meta,
                                          EntityMeta entityMeta) {
        IronContracts.notNull(entityClass, "Entity class");
        IronContracts.notNull(entityProvider, "Entity provider");
        IronContracts.notNull(meta, "Page meta");
        IronContracts.notNull(entityMeta, "Property Schema");
    }

    private static Map<String, PropertyDefinition> buildOverridesMap(Collection<PropertyDefinition> overrides) {
        Map<String, PropertyDefinition> overridesMap = new HashMap<>(overrides.size());
        overrides.forEach((od) -> overridesMap.put(od.getName(), od));
        return overridesMap;
    }


    @NotNull
    @Override
    public List<PropertyDefinition> getProperties() {
        return IronObjects.streamPropertyNames(entityClass)
                .filter((name) -> !skippedProperties.contains(name))
                .map((name) -> definitionOverrides.getOrDefault(name, new PropertyDefinition(name)))
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public final String getTitle() {
        return title;
    }

    @NotNull
    @Override
    public String getUrl() {
        return url;
    }

    @NotNull
    @Override
    public EntityProvider getProvider() {
        return entityProvider;
    }
}
