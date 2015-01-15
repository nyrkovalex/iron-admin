package com.github.nyrkovalex.ironadmin.core.defaults;

import com.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;

import java.util.Collection;

public class EditPageContext<T> extends DefaultPageContext {
    private final T entity;

    public EditPageContext(T entity,
                           String pageTitle,
                           Collection<PropertyDefinition> properties,
                           String idPropertyName,
                           String url) {
        super(pageTitle, properties, idPropertyName, url);
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    @Override
    public String templatePath() {
        return "ui/templates/default/html/edit.html";
    }

}
