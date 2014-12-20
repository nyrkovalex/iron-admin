package org.github.nyrkovalex.ironadmin.core.pages;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ConstantConditions")
public class PageUrlTest {

  private PageUrl pageUrl;

  @Before
  public void setUp() throws Exception {
    pageUrl = PageUrl.of(PageUrlTest.class);
  }

  @Test
  public void testShouldUseClassNameAsPageUrl() throws Exception {
    assertThat(pageUrl.getUrl(), is("/page-url-tests"));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowIfUrlIsNull() throws Exception {
    String url = null;
    PageUrl.of(url);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowIfClassIsNull() throws Exception {
    Class<?> clazz = null;
    PageUrl.of(clazz);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testShouldThrowWhenConstructingWithEmptyUrl() throws Exception {
    PageUrl.of("");
  }

}
