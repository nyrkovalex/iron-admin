package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.TemplateResolver;
import org.github.nyrkovalex.ironadmin.core.pages.Page;
import org.github.nyrkovalex.ironutils.IronContracts;
import org.jetbrains.annotations.NotNull;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;

import java.io.Writer;

class ThymeleafTemplateResolver implements TemplateResolver {

    public static final String UI_OUT_DIR = "ui/templates/";
    private final TemplateEngine templateEngine;
    private final IronAdminEnvironment env;

    public ThymeleafTemplateResolver(IronAdminEnvironment env) {
        org.thymeleaf.templateresolver.TemplateResolver templateResolver = createThymeleafResolver();
        templateEngine = createThymeleafEngine(templateResolver);
        this.env = env;
    }

    private TemplateEngine createThymeleafEngine(org.thymeleaf.templateresolver.TemplateResolver templateResolver) {
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    private org.thymeleaf.templateresolver.TemplateResolver createThymeleafResolver() {
        org.thymeleaf.templateresolver.TemplateResolver templateResolver =
                new org.thymeleaf.templateresolver.TemplateResolver();
        templateResolver.setPrefix(UI_OUT_DIR);
        templateResolver.setSuffix("/html/template.html");
        templateResolver.setResourceResolver(new ClassLoaderResourceResolver());
        return templateResolver;
    }

    @Override
    public void resolvePageTemplate(String urlPrefix, @NotNull Page page, @NotNull Writer writer) {
        IronContracts.notNull(page, "page", writer, "writer");

        IContext context = new PageContext(page, urlPrefix);
        templateEngine.process(page.getTemplateName(), context, writer);
    }

    private class PageContext extends Context {

        public PageContext(Page page, String urlPrefix) {
            setVariable("page", page);
            setVariable("ia", env);
            setVariable("iaUrl", new UrlResolver(urlPrefix));
        }

        private class UrlResolver {
            private final String prefix;

            public UrlResolver(String urlPrefix) {
                this.prefix = urlPrefix;
            }

            @SuppressWarnings("UnusedDeclaration")
            public String resolve(String url) {
                return prefix + url;
            }
        }
    }
}
