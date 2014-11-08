package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironutils.IronContracts;
import org.github.nyrkovalex.ironutils.IronStrings;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class EntityMeta {

    private final String title;
    private final Collection<PropertyDefinition> overrides;
    private final Collection<String> skippedProperties;

    private EntityMeta(String title,
                       Collection<PropertyDefinition> overrides,
                       Collection<String> skippedProperties) {
        this.title = title;
        this.overrides = overrides;
        this.skippedProperties = skippedProperties;
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

    public static EntityMeta of(@NotNull Class<?> entityClass) {
        return of(entityClass,
                Optional.<Collection<PropertyDefinition>>empty(), Optional.<Collection<String>>empty()
        );
    }

    public static EntityMeta of(@NotNull Class<?> entityClass,
                                @NotNull Collection<String> skippedProperties) {
        IronContracts.notNull(skippedProperties, "skipped properties");

        return of(entityClass,
                Optional.<Collection<PropertyDefinition>>empty(), Optional.of(skippedProperties)
        );
    }

    public static EntityMeta of(@NotNull Class<?> entityClass,
                                @NotNull Optional<Collection<PropertyDefinition>> overrides,
                                @NotNull Optional<Collection<String>> skippedProperties) {
        return of(entityClass, Optional.empty(), overrides, skippedProperties);
    }

    public static EntityMeta of(@NotNull Class<?> entityClass,
                                @NotNull Optional<String> title,
                                @NotNull Optional<Collection<PropertyDefinition>> overrides,
                                @NotNull Optional<Collection<String>> skippedProperties) {
        IronContracts.notNull(entityClass, "class", title, "title");
        IronContracts.notNull(overrides, "overrides", skippedProperties, "skipped");

        Collection<String> skippedValue = skippedProperties.orElse(Collections.emptyList());
        Collection<PropertyDefinition> overridesValue = overrides.orElse(Collections.emptyList());

        if (title.isPresent()) {
            String titleValue = title.get();
            return of(titleValue, overridesValue, skippedValue);
        }

        return of(IronStrings.camelCaseToSentence(entityClass.getSimpleName()),
                overridesValue, skippedValue);
    }

    public static EntityMeta of(@NotNull String title,
                                @NotNull Collection<PropertyDefinition> overrides,
                                @NotNull Collection<String> skippedProperties) {
        IronContracts.notNullOrEmpty(title, "title");
        IronContracts.notNull(overrides, "overrides", skippedProperties, "skipped properties");

        return new EntityMeta(title, overrides, skippedProperties);
    }
}
