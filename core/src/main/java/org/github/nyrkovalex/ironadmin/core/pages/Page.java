package org.github.nyrkovalex.ironadmin.core.pages;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * This interface describes admin pages. To register a pages one should implement this interface
 * and register its implementation within {@link org.github.nyrkovalex.ironadmin.core.pages.PageRegistry PageRegistry}
 * implementation retrieved from {@link org.github.nyrkovalex.ironadmin.core.AdminContext AdminContext}.
 *
 * @param <T> type of an entity this page belongs to
 *
 * @see org.github.nyrkovalex.ironadmin.core.pages.PageRegistry PageRegistry
 * @see org.github.nyrkovalex.ironadmin.core.AdminContext AdminContext
 * @see org.github.nyrkovalex.ironadmin.core.defaults.DefaultContext DefaultContext
 *
 */
@SuppressWarnings("UnusedDeclaration")
public interface Page<T> {
    /**
     * Page title to be displayed in the menu and as a pages header
     *
     * @return title of the current pages
     */
    @NotNull
    String getTitle();

    /**
     * <p>Url this pages will be mapped to. Expected to start from forward slash character.</p>
     *
     *  <p>This url will be registered under
     * {@link org.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet}
     * mapping.<br />
     * E.g. if pages url is <code>"/users"</code> and
     * {@link org.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet} is
     * mapped to <code>"/admin/*"</code> then this pages will be available under <code>"/admin/users</code></p>
     *
     * @return url of a current pages.
     *
     * @see org.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet
     */
    @NotNull
    String getUrl();

    /**
     * Name of a template used to render current pages. This value may differ depending on
     * {@link org.github.nyrkovalex.ironadmin.core.TemplateResolver TemplateResolver} implementation
     *
     * @return name of a template used for this pages
     *
     * @see org.github.nyrkovalex.ironadmin.core.TemplateResolver TemplateResolver
     * @see org.github.nyrkovalex.ironadmin.core.defaults.ThymeleafTemplateResolver ThymeleafTemplateResolver
     * @see org.github.nyrkovalex.ironadmin.core.AdminContext AdminContext
     */
    @NotNull
    String getTemplateName();

    @NotNull
    List<PropertyDefinition> getProperties();

    @NotNull
    Collection<T> getEntities();
}
