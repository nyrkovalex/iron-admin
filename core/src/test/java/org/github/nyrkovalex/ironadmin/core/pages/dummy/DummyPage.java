package org.github.nyrkovalex.ironadmin.core.pages.dummy;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("ConstantConditions")
public class DummyPage implements Page {
  private final String url;

  public DummyPage() {
    this("/dummy");
  }

  public DummyPage(String url) {
    this.url = url;
  }

  @NotNull
  @Override
  public String getTitle() {
    return "Dummy";
  }

  @NotNull
  @Override
  public String getUrl() {
    return url;
  }

  @NotNull
  @Override
  public String getTemplateName() {
    return "dummy";
  }

  @NotNull
  @Override
  public List<PropertyDefinition> getProperties() {
    return null;
  }

  @NotNull
  @Override
  public EntityProvider getProvider() {
    return null;
  }

  @NotNull
  @Override
  public String getIdPropertyName() {
    return null;
  }
}
