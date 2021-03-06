package com.novoda.imageloader.core.cache;

import java.util.HashMap;

import android.graphics.Bitmap;

public class MapCache implements ImageCache {

  private HashMap<String, Bitmap> cache = new HashMap<String, Bitmap>();
  private Bitmap defaultImage;

  @Override
  public boolean hasImage(String url) {
    return cache.containsKey(url);
  }

  @Override
  public Bitmap get(String url) {
    return cache.get(url);
  }

  @Override
  public void put(String url, Bitmap bmp) {
    cache.put(url, bmp);
  }

  @Override
  public void clean() {
    cache.clear();
  }

  @Override
  public Bitmap getDefaultImage() {
    return defaultImage;
  }

  @Override
  public void setDefaultImage(Bitmap defaultImage) {
    this.defaultImage = defaultImage;
  }
  
}
