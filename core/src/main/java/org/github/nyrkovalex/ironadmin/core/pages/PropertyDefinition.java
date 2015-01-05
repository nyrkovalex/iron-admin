package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironutils.IronContracts;
import org.github.nyrkovalex.ironutils.IronStrings;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.displayName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PropertyDefinition other = (PropertyDefinition) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.displayName, other.displayName);
    }


}
