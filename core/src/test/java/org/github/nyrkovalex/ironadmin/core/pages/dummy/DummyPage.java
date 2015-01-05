package org.github.nyrkovalex.ironadmin.core.pages.dummy;

import org.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironadmin.core.pages.PageContext;
import org.github.nyrkovalex.ironadmin.core.pages.PageRequest;
import org.github.nyrkovalex.ironadmin.core.pages.PropertyDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("ConstantConditions")
public class DummyPage implements Page {
    private final String url;

    public DummyPage() {
        this("/dummy");
    }

    public DummyPage(String url) {
        this.url = url;
    }

    @NotNull
    @Override
    public String title() {
        return "Dummy";
    }

    @NotNull
    @Override
    public String url() {
        return url;
    }

    @NotNull
    @Override
    public List<PropertyDefinition> properties() {
        return null;
    }

    @NotNull
    @Override
    public EntityProvider provider() {
        return null;
    }

    @NotNull
    @Override
    public String idPropertyName() {
        return null;
    }

    @Override
    @NotNull
    public PageContext pageContextForRequest(PageRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
