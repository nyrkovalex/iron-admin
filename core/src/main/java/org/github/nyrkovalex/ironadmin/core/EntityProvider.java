package org.github.nyrkovalex.ironadmin.core;

import java.util.List;

public interface EntityProvider<T, ID> {
  @SuppressWarnings("UnusedDeclaration")
  List<T> all();

  T byId(ID id);
}
