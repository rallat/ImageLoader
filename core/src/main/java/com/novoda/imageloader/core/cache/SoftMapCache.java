package com.novoda.imageloader.core.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;

public class SoftMapCache implements ImageCache {

  private HashMap<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();

  @Override
  public boolean hasImage(String url) {
    if (cache.containsKey(url)) {
      if (cache.get(url) != null) {
        return true;
      }
      return false;
    }
    return false;
  }

  @Override
  public Bitmap get(String url) {
    SoftReference<Bitmap> bmpr = cache.get(url);
    if (bmpr != null) {
      return bmpr.get();
    }
    return null;
  }

  @Override
  public void put(String url, Bitmap bmp) {
    cache.put(url, new SoftReference<Bitmap>(bmp));
  }

  @Override
  public void clean() {
    cache.clear();
  }

}
