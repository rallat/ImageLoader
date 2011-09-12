package com.novoda.imageloader.core.cache;

import android.graphics.Bitmap;

public class NoCache implements ImageCache {

  @Override
  public boolean hasImage(String url) {
    return false;
  }

  @Override
  public Bitmap get(String url) {
    return null;
  }

  @Override
  public void put(String url, Bitmap bmp) {
  }

  @Override
  public void clean() {
  }

  @Override
  public Bitmap getDefaultImage() {
    return null;
  }

  @Override
  public void setDefaultImage(Bitmap defaultImage) {
  
  }

}
