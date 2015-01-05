package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;

public interface PageBuilder<T> {
    PageBuilder<T> titled(String title);

    PageBuilder<T> putAt(String url);

    PageBuilder<T> override(PropertyDefinition... overrides);

    PageBuilder<T> backedBy(EntityProvider<T> entityProvider);

    PageBuilder<T> skip(String... propertyNames);

    Page<T> build();
}
