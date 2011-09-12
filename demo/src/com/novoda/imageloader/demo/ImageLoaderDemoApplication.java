package com.novoda.imageloader.demo;

import android.app.Application;

import com.novoda.imageloader.core.BaseImageLoader;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.Settings;
import com.novoda.imageloader.core.SettingsBuilder;

public class ImageLoaderDemoApplication extends Application {

  // TODO add this to your class
  private static ImageManager imageLoader;

  @Override
  public void onCreate() {
    super.onCreate();
    // TODO add this to your classs
    SettingsBuilder builder = new SettingsBuilder();
    builder.setDefaultImageId(R.drawable.bg_img_loading);
    Settings settings = builder.build(this);
    imageLoader = new BaseImageLoader(this, settings);
    //
  }

  // TODO add this to your class
  public static ImageManager getImageLoader() {
    return imageLoader;
  }
  
}
