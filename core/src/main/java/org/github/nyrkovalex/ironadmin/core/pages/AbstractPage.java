package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironutils.IronContracts;
import org.github.nyrkovalex.ironutils.IronObjects;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractPage<T, ID> implements Page<T, ID> {
  private final String title;
  private final String url;
  private final Class<T> entityClass;
  private final EntityProvider<T, ID> entityProvider;
  private final Collection<String> skippedProperties;
  private final Map<String, PropertyDefinition> definitionOverrides;
  private final String idPropertyName;

  protected AbstractPage(@NotNull Class<T> entityClass,
                         @NotNull EntityProvider<T, ID> entityProvider)
  {
    this(entityClass, entityProvider, PageMapping.of(entityClass), EntityMeta.of(entityClass).build());
  }


  protected AbstractPage(@NotNull Class<T> entityClass,
                         @NotNull EntityProvider<T, ID> entityProvider,
                         @NotNull PageMapping pageMapping)
  {
    this(entityClass, entityProvider, pageMapping, EntityMeta.of(entityClass).build());
  }

  protected AbstractPage(@NotNull Class<T> entityClass,
                         @NotNull EntityProvider<T, ID> entityProvider,
                         @NotNull EntityMeta meta)
  {
    this(entityClass, entityProvider, PageMapping.of(entityClass), meta);
  }

  protected AbstractPage(@NotNull Class<T> entityClass,
                         @NotNull EntityProvider<T, ID> entityProvider,
                         @NotNull PageMapping pageMapping,
                         @NotNull EntityMeta entityMeta)
  {
    validateContracts(entityClass, entityProvider, pageMapping, entityMeta);

    this.title = entityMeta.getTitle();
    this.url = pageMapping.getUrl();
    this.entityClass = entityClass;
    this.entityProvider = entityProvider;
    this.skippedProperties = entityMeta.getSkippedProperties();
    this.definitionOverrides = buildOverridesMap(entityMeta.getOverrides());
    this.idPropertyName = entityMeta.getIdPropertyName();
  }

  private static void validateContracts(Class<?> entityClass,
                                        EntityProvider<?, ?> entityProvider,
                                        PageMapping meta,
                                        EntityMeta entityMeta)
  {
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
  public EntityProvider<T, ID> getProvider() {
    return entityProvider;
  }

  @NotNull
  @Override
  public String getIdPropertyName() {
    return idPropertyName;
  }
}
