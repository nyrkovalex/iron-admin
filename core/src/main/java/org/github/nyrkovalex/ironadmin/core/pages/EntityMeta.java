package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironutils.IronContracts;
import org.github.nyrkovalex.ironutils.IronStrings;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class EntityMeta {

  public static final String DEFAULT_ID_PROPERTY_NAME = "id";

  private final String title;
  private final Collection<PropertyDefinition> overrides;
  private final Collection<String> skippedProperties;
  private final String idPropertyName;

  private EntityMeta(String title,
                     Collection<PropertyDefinition> overrides,
                     Collection<String> skippedProperties,
                     String idPropertyName)
  {
    this.title = title;
    this.overrides = overrides;
    this.skippedProperties = skippedProperties;
    this.idPropertyName = idPropertyName;
  }

  public Collection<PropertyDefinition> getOverrides() {
    return overrides;
  }

  public Collection<String> getSkippedProperties() {
    return skippedProperties;
  }

  public String getTitle() {
    return title;
  }


  public String getIdPropertyName() {
    return idPropertyName;
  }

  public static Builder of(@NotNull Class<?> entityClass) {
    return new Builder(entityClass);
  }

  public static final class Builder {

    private String title;
    private Collection<PropertyDefinition> overrides;
    private Collection<String> skippedProperties;
    private String idPropertyName;

    public Builder(@NotNull Class<?> entityClass) {
      IronContracts.notNull(entityClass, "entity class");
      this.title = IronStrings.camelCaseToSentence(entityClass.getSimpleName());
      overrides = Collections.emptyList();
      skippedProperties = Collections.emptyList();
      idPropertyName = DEFAULT_ID_PROPERTY_NAME;
    }

    public Builder overrides(@NotNull Collection<PropertyDefinition> overrides) {
      IronContracts.notNull(overrides, "overrides");
      this.overrides = new ArrayList<>(overrides);
      return this;
    }

    public Builder overrides(PropertyDefinition... overrides) {
      return overrides(Arrays.asList(overrides));
    }

    public Builder skips(@NotNull Collection<String> skips) {
      IronContracts.notNull(skips, "skips");
      this.skippedProperties = new ArrayList<>(skips);
      return this;
    }

    public Builder skips(String... skips) {
      return skips(Arrays.asList(skips));
    }

    public Builder idPropertyName(String idPropertyName) {
      IronContracts.notNullOrEmpty(idPropertyName, "id property name");
      this.idPropertyName = idPropertyName;
      return this;
    }

    public Builder title(String title) {
      IronContracts.notNullOrEmpty(title, "title");
      this.title = title;
      return this;
    }

    public EntityMeta build() {
      return new EntityMeta(title, overrides, skippedProperties, idPropertyName);
    }
  }
}
