package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.pages.PageContext;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;

import java.util.Collection;

public abstract class DefaultPageContext implements PageContext {

    private final String pageTitle;
    private final Collection<PropertyDefinition> properties;
    private final String idPropertyName;
    private final String url;

    DefaultPageContext(String pageTitle,
                       Collection<PropertyDefinition> properties,
                       String idPropertyName,
                       String url) {
        this.pageTitle = pageTitle;
        this.properties = properties;
        this.idPropertyName = idPropertyName;
        this.url = url;
    }

    public String getTitle() {
        return pageTitle;
    }

    public Collection<PropertyDefinition> getProperties() {
        return properties;
    }

    public String getIdPropertyName() {
        return idPropertyName;
    }

    public String getUrl() {
        return url;
    }
}
