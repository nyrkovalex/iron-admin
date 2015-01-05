package org.github.nyrkovalex.ironadmin.core;

import org.github.nyrkovalex.ironadmin.core.pages.PageContext;

import java.io.Writer;

public interface TemplateResolver {

    /**
     * <p>Resolves the template against {@link org.github.nyrkovalex.ironadmin.core.pages.Page Page} provided.</p>
     * <p>
     * <p>Implementors must take into account that
     * {@link org.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet} may be
     * registered under different paths therefore each time template is resolved it will provide target
     * {@link org.github.nyrkovalex.ironadmin.core.TemplateResolver TemplateResolver} implementation with its
     * current path providing <code>urlPrefix</code> parameter.</p>
     *
     * @param urlPrefix   current mapping of
     *                    {@link org.github.nyrkovalex.ironadmin.core.servlet.AdminDispatcherServlet AdminDispatcherServlet}.
     *                    All relative links inside target <code>pages</code> must be prefixed with tis path.
     * @param pageContext current admin page context
     * @param writer      writer to write resolved template to. Most likely but not necessary
     *                    {@link java.io.PrintWriter PrintWriter} from {@link javax.servlet.http.HttpServletResponse HttpServletResponse}
     */
    void resolvePageTemplate(String urlPrefix, PageContext pageContext, Writer writer);
}
