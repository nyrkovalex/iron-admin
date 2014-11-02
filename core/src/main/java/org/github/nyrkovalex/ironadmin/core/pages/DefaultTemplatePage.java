package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironutils.IronObjects;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class DefaultTemplatePage<T> implements Page<T> {
    public static final String DEFAULT_TEMPLATE_NAME = "default";
    private final String title;
    private final String url;
    private final Class<T> entityClass;
    private Map<String, PropertyDefinition> definitionOverrides;

    protected DefaultTemplatePage(String title, String url, Class<T> entityClass) {
        this.title = title;
        this.url = url;
        this.entityClass = entityClass;
        definitionOverrides = new HashMap<>();
    }


    protected void overridePropertyDefinitions(PropertyDefinition... definitions) {
        for (PropertyDefinition pd : definitions) {
            definitionOverrides.put(pd.getName(), pd);
        }
    }


    @NotNull
    @Override
    public String getTitle() {
        return title;
    }

    @NotNull
    @Override
    public String getUrl() {
        return url;
    }

    @NotNull
    @Override
    public String getTemplateName() {
        return DEFAULT_TEMPLATE_NAME;
    }

    @NotNull
    @Override
    public List<PropertyDefinition> getProperties() {
        return IronObjects.streamPropertyNames(entityClass).map((name) -> {
            //noinspection CodeBlock2Expr
            return definitionOverrides.getOrDefault(name, new PropertyDefinition(name));
        }).collect(Collectors.toList());
    }
}
