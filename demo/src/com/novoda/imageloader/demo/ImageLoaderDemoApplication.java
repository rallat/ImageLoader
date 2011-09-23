package com.novoda.imageloader.demo;

import android.app.Application;

import com.novoda.imageloader.core.BaseImageLoader;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.Settings;
import com.novoda.imageloader.core.SettingsBuilder;

public class ImageLoaderDemoApplication extends Application {

  // TODO add this to your class
  private static ImageManager imageLoader;
  private static ImageManager imageLoaderWithQueryRemoved;

  @Override
  public void onCreate() {
    super.onCreate();
    // TODO add this to your classs
    SettingsBuilder builder = new SettingsBuilder();
    builder.defaultImageId(R.drawable.bg_img_loading);
    Settings settings = builder.build(this);
    imageLoader = new BaseImageLoader(this, settings);
    //
    
    // TODO is possible to have more than one image loader
    builder = new SettingsBuilder();
    builder.defaultImageId(R.drawable.bg_img_loading);
    settings = builder.build(this);
    settings.setQueryIncludedInHash(false);
    imageLoaderWithQueryRemoved = new BaseImageLoader(this, settings);
    //
  }

  // TODO add this to your class
  public static ImageManager getImageLoader() {
    return imageLoader;
  }
  
  // TODO add this to your class
  public static ImageManager getImageLoaderWithQueryRemoved() {
    return imageLoaderWithQueryRemoved;
  }
  
}
