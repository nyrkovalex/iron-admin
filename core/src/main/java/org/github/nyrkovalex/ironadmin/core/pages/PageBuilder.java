package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;

public interface PageBuilder<T, ID> {
  PageBuilder<T, ID> titled(String title);

  PageBuilder<T, ID> putAt(String url);

  PageBuilder<T, ID> override(PropertyDefinition... overrides);

  PageBuilder<T, ID> backedBy(EntityProvider<T, ID> entityProvider);

  PageBuilder<T, ID> skip(String... propertyNames);

  Page<T, ID> build();
}
