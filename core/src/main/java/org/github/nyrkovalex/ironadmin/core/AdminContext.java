package org.github.nyrkovalex.ironadmin.core;

import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;
import org.jetbrains.annotations.NotNull;

public interface AdminContext {
  @NotNull
  PageRegistry getRegistry();

  @NotNull
  TemplateResolver getTemplateResolver();

  void setTitle(@NotNull String title);

  @NotNull
  String getTitle();

  /**
   * Registers multiple pages within admin application
   *
   * @param pages pages to register
   */
  void register(@NotNull Page... pages);

}
