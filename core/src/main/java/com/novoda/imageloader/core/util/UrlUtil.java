package com.novoda.imageloader.core.util;

public class UrlUtil {

  private static final String QUERY_DELIMITER = "?";
  
  public String removeQuery(String url) {
    if(url == null) {
      return url;
    }
    int index = url.indexOf(QUERY_DELIMITER);
    if(index == -1) {
      return url;
    }
    return url.substring(0, index);
  }
  
  

}
