package org.github.nyrkovalex.ironadmin.core.defaults;

import org.github.nyrkovalex.ironadmin.core.TemplateResolver;
import org.github.nyrkovalex.ironadmin.core.pages.PageContext;
import org.github.nyrkovalex.ironutils.IronContracts;
import org.jetbrains.annotations.NotNull;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;

import java.io.Writer;

class ThymeleafTemplateResolver implements TemplateResolver {

    private final TemplateEngine templateEngine;
    private final EnvironmentContext env;

    public ThymeleafTemplateResolver(EnvironmentContext env) {
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
        templateResolver.setResourceResolver(new ClassLoaderResourceResolver());
        return templateResolver;
    }

    @Override
    public void resolvePageTemplate(String urlPrefix, @NotNull PageContext pageContext, @NotNull Writer writer) {
        IronContracts.notNull(pageContext, "context", writer, "writer");

        IContext thymeleafContext = new MainPageContext(pageContext, urlPrefix, env);
        templateEngine.process(pageContext.templatePath(), thymeleafContext, writer);
    }

    private static class MainPageContext extends Context {

        public MainPageContext(PageContext context, String urlPrefix, EnvironmentContext env) {
            setVariable("page", context);
            setVariable("ia", env);
            setVariable("iaUrl", new UrlResolver(urlPrefix));
        }
    }

    private static class UrlResolver {
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
