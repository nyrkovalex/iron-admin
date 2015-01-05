package org.github.nyrkovalex.ironadmin.core;

import java.util.List;

public interface EntityProvider<T> {
    @SuppressWarnings("UnusedDeclaration")
    List<T> all();

    T one(String id);
}
