package com.github.nyrkovalex.ironadmin.core.defaults;

import com.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;

import java.util.Collection;

class MainPageContext<T> extends DefaultPageContext {
    private final Collection<T> entities;

    public MainPageContext(Collection<T> entities,
                           String pageTitle,
                           Collection<PropertyDefinition> properties,
                           String idPropertyName,
                           String url) {
        super(pageTitle, properties, idPropertyName, url);
        this.entities = entities;
    }

    public Collection<T> getEntities() {
        return entities;
    }

    @Override
    public String templatePath() {
        return "ui/templates/default/html/index.html";
    }
}
