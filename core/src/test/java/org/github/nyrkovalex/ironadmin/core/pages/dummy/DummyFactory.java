package org.github.nyrkovalex.ironadmin.core.pages.dummy;

import org.github.nyrkovalex.ironadmin.core.TemplateResolver;
import org.github.nyrkovalex.ironadmin.core.pages.PageRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

public final class DummyFactory {

  private static PageRegistry registry;
  private static TemplateResolver resolver;
  private static HttpServletRequest servletRequest;
  private static HttpServletResponse servletResponse;

  private DummyFactory() {

  }

  public static void reset() {
    servletResponse = mock(HttpServletResponse.class);
    servletRequest = mock(HttpServletRequest.class);
    resolver = mock(TemplateResolver.class);
    registry = mock(PageRegistry.class);
  }

  public static PageRegistry getRegistry() {
    return registry;
  }

  public static TemplateResolver getResolver() {
    return resolver;
  }

  public static HttpServletRequest getRequest() {
    return servletRequest;
  }

  public static HttpServletResponse getResponse() {
    return servletResponse;
  }
}
