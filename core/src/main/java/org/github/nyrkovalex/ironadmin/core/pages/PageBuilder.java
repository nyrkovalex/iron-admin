package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;

public interface PageBuilder<E> {
  PageBuilder<E> titled(String title);

  PageBuilder<E> putAt(String url);

  PageBuilder<E> override(PropertyDefinition... overrides);

  PageBuilder<E> backedBy(EntityProvider<E> entityProvider);

  PageBuilder<E> skip(String... propertyNames);

  Page<E> build();
}
