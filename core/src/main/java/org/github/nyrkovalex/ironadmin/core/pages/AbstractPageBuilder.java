package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class AbstractPageBuilder<E> implements PageBuilder<E> {
  private final Class<E> entityClass;
  private Optional<String> title;
  private Optional<String> url;
  private Collection<PropertyDefinition> overrides;
  private EntityProvider<E> entityProvider;
  private Collection<String> skipped;

  protected AbstractPageBuilder(Class<E> entityClass) {
    this.entityClass = entityClass;
    overrides = Collections.emptyList();
    title = Optional.empty();
  }

  @Override
  public AbstractPageBuilder<E> titled(String title) {
    this.title = Optional.of(title);
    return this;
  }

  @Override
  public AbstractPageBuilder<E> putAt(String url) {
    this.url = Optional.of(url);
    return this;
  }

  @Override
  public AbstractPageBuilder<E> override(PropertyDefinition... overrides) {
    this.overrides = Arrays.asList(overrides);
    return this;
  }

  @Override
  public AbstractPageBuilder<E> backedBy(EntityProvider<E> entityProvider) {
    this.entityProvider = entityProvider;
    return this;
  }

  @Override
  public PageBuilder<E> skip(String... propertyNames) {
    this.skipped = Arrays.asList(propertyNames);
    return this;
  }

  protected Class<E> getEntityClass() {
    return entityClass;
  }

  protected EntityMeta getEntityMeta() {
    return EntityMeta.of(
        entityClass,
        title,
        Optional.ofNullable(overrides),
        Optional.ofNullable(skipped)
    );
  }

  protected PageUrl getUrl() {
    if (url.isPresent()) {
      return PageUrl.of(url.get());
    }
    return PageUrl.of(entityClass);
  }

  protected EntityProvider<E> getEntityProvider() {
    return entityProvider;
  }

}
