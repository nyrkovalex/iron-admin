package org.github.nyrkovalex.ironadmin.core.utils;

import java.util.Collection;

/**
 * Various method contract validation helpers
 */
public final class IronContracts {
    private IronContracts() {

    }

    /**
     * Verifies target object for null reference
     *
     * @param o            object to verify
     * @param argumentName name to be displayed in the exception message
     * @throws IllegalArgumentException thrown if validity check fails
     */
    public static void notNull(Object o, String argumentName) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException(argumentName + " must not be null");
        }
    }

    /**
     * Verifies two objects for null reference
     *
     * @param o1            first object to verify
     * @param argumentName1 name to be displayed in the exception message if first object fails
     * @param o2            second object to verify
     * @param argumentName2 name to be displayed in the exception message if second object fails
     * @throws IllegalArgumentException thrown if validity check fails
     */
    public static void notNull(Object o1, String argumentName1, Object o2, String argumentName2) throws IllegalArgumentException {
        notNull(o1, argumentName1);
        notNull(o2, argumentName2);
    }

    /**
     * Verifies target string for null reference and emptiness
     *
     * @param string       string to verify
     * @param argumentName name to be displayed in the exception message
     * @throws IllegalArgumentException thrown if validity check fails
     */
    public static void notNullOrEmpty(String string, String argumentName) throws IllegalArgumentException {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(argumentName + " must not be null or empty");
        }
    }

    /**
     * Verifies two target strings for null reference and emptiness
     *
     * @param string1       first string to verify
     * @param argumentName1 name to be displayed in the exception message if first string fails
     * @param string2       second string to verify
     * @param argumentName2 name to be displayed in the exception message if second string fails
     * @throws IllegalArgumentException thrown if validity check fails
     */
    public static void notNullOrEmpty(String string1, String argumentName1,
            String string2, String argumentName2) throws IllegalArgumentException {
        notNullOrEmpty(string1, argumentName1);
        notNullOrEmpty(string2, argumentName2);
    }

    /**
     * Verifies that target array is not null or empty
     *
     * @param arr          array to verify
     * @param argumentName name to be displayed in the exception message
     * @throws IllegalArgumentException thrown if validity check fails
     */
    public static void notNullOrEmpty(Object[] arr, String argumentName) throws IllegalArgumentException {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException(argumentName + "must not be null or empty");
        }
    }

    /**
     * Verifies that target collection is not null or empty
     *
     * @param c            collection to verify
     * @param argumentName name to be displayed in the exception message
     * @throws IllegalArgumentException thrown if validity check fails
     */
    public static void notNullOrEmpty(Collection<?> c, String argumentName) throws IllegalArgumentException {
        if (c == null || c.size() == 0) {
            throw new IllegalArgumentException(argumentName + "must not be null or empty");
        }
    }
}
