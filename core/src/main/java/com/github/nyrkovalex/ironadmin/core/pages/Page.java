package com.github.nyrkovalex.ironadmin.core.pages;

import com.github.nyrkovalex.ironadmin.core.EntityProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This interface describes admin pages. To register a pages one should implement this interface
 * and register its implementation within {@link PageRegistry PageRegistry}
 * implementation retrieved from {@link com.github.nyrkovalex.ironadmin.core.AdminContext AdminContext}.
 *
 * @param <T> type of an entity this page belongs to
 * @see PageRegistry PageRegistry
 * @see com.github.nyrkovalex.ironadmin.core.AdminContext AdminContext
 * @see com.github.nyrkovalex.ironadmin.core.defaults.DefaultAdminContext DefaultContext
 */
public interface Page<T> {
    /**
     * Page title to be displayed in the menu and as a pages header
     *
     * @return title of the current pages
     */
    @NotNull
    String title();

    /**
     * <p>Url this pages will be mapped to. Expected to start from forward slash character.</p>
     * <p>
     * <p>This url will be registered under
     * {@link com.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet}
     * mapping.<br />
     * E.g. if pages url is <code>"/users"</code> and
     * {@link com.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet} is
     * mapped to <code>"/admin/*"</code> then this pages will be available under <code>"/admin/users</code></p>
     *
     * @return url of a current pages.
     * @see com.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet
     */
    @NotNull
    String url();

    @NotNull
    PageContext pageContextForRequest(PageRequest request);

    @NotNull
    List<PropertyDefinition> properties();

    @NotNull
    EntityProvider<T> provider();

    @NotNull
    String idPropertyName();
}
