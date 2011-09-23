package com.novoda.imageloader.core.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UrlUtilTest {
  
  private UrlUtil urlUtil = new UrlUtil();
  
  @Test
  public void SHOULD_removeQueryFromUrlReturnNul_WHEN_urlIsNull() {
    assertEquals(null, urlUtil.removeQuery(null));
  }
  
  @Test
  public void SHOULD_removeQueryFromUrlReturnUnmodifiedUrl_WHEN_qeryNotPresent() {
    assertEquals("http://www.google.com", urlUtil.removeQuery("http://www.google.com"));
  }
  
  @Test
  public void SHOULD_removeQueryFromUrl() {
    assertEquals("http://www.google.com", urlUtil.removeQuery("http://www.google.com?q=test"));
  }

}
