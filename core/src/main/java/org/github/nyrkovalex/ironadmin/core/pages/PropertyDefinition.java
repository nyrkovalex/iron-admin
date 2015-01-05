package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironutils.IronContracts;
import org.github.nyrkovalex.ironutils.IronStrings;
import org.jetbrains.annotations.NotNull;

public class PropertyDefinition {
    private final String name;
    private final String displayName;

    public PropertyDefinition(@NotNull String name) {
        this(name, IronStrings.camelCaseToSentence(name));
    }

    public PropertyDefinition(@NotNull String name, @NotNull String displayName) {
        IronContracts.notNullOrEmpty(name, "name", displayName, "displayName");

        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
