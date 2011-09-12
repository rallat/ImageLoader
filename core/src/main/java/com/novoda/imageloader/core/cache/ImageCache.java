package com.novoda.imageloader.core.cache;

import android.graphics.Bitmap;

public interface ImageCache {

  boolean hasImage(String url);

  Bitmap get(String url);

  void put(String url, Bitmap bmp);

  void clean();
  
  Bitmap getDefaultImage();
  
  void setDefaultImage(Bitmap defaultImage);

}
