package org.github.nyrkovalex.ironadmin.core;

import java.beans.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IronObjects {
    private IronObjects() { }

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
        List<PropertyDescriptor> propertyDescriptors = Arrays.asList(beanInfo.getPropertyDescriptors());
        return propertyDescriptors.stream()
                .map(FeatureDescriptor::getDisplayName)
                .filter((name) -> !name.equals("class"));
    }
}
