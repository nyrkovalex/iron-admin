package org.github.nyrkovalex.ironadmin.core.pages;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironutils.IronObjects;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultTemplatePage<T> implements Page<T> {
    public static final String DEFAULT_TEMPLATE_NAME = "default";
    private final String title;
    private final String url;
    private final Class<T> entityClass;
    private final EntityProvider<T> entityProvider;
    private Map<String, PropertyDefinition> definitionOverrides;

    protected DefaultTemplatePage(String title, String url, Class<T> entityClass, EntityProvider<T> entityProvider) {
        this.title = title;
        this.url = url;
        this.entityClass = entityClass;
        this.entityProvider = entityProvider;
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
    public EntityProvider getProvider() {
        return entityProvider;
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

    public static <E> PageBuilder<E> createFor(Class<E> entityClass) {
        return new PageBuilder<>(entityClass);
    }

    public static class PageBuilder<E> extends AbstractPageBuilder<E> {

        public PageBuilder(Class<E> entityClass) {
            super(entityClass);
        }

        @Override
        public Page<E> build() {
            DefaultTemplatePage<E> page = new DefaultTemplatePage<>(getTitle(), getUrl(),
                    getEntityClass(), getEntityProvider());
            page.overridePropertyDefinitions(getOverrides());
            return page;
        }
    }
}
