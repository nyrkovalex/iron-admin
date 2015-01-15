package org.github.nyrkovalex.ironadmin.core.utils;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IronObjects {
    private IronObjects() {
    }

    public static List<String> getPropertyNames(Class<?> clazz) {
        return streamPropertyNames(clazz).collect(Collectors.toList());
    }

    public static Stream<String> streamPropertyNames(Class<?> clazz) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new AssertionError("Should not happen");
        }
        return Arrays.stream(beanInfo.getPropertyDescriptors())
                .map(FeatureDescriptor::getDisplayName)
                .filter((name) -> !name.equals("class"));
    }
}
