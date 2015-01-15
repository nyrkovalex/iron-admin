package org.github.nyrkovalex.ironadmin.core.utils;

import java.util.Map;

/**
 * Helper functions for maps
 */
public final class IronMaps {
    private IronMaps() {
    }

    /**
     * Puts provided <code>value</code> into the <code>map</code> under <code>key</code> if there was no
     * value stored under they. Otherwise throws {@link java.lang.IllegalArgumentException IllegalArgumentException}
     *
     * @param map   map to put value to
     * @param key   key under which the value will be stored
     * @param value value to put into the map
     * @param <K>   key type
     * @param <V>   value type
     * @throws IllegalArgumentException if <code>key</code> is already present in the <code>map</code>
     */
    public static <K, V> void putOrThrowIfPresent(Map<K, V> map, K key, V value) throws IllegalArgumentException {
        if (map.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Key %s is already present", key));
        }
        map.put(key, value);
    }
}
