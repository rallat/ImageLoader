package com.novoda.imageloader.core;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.novoda.imageloader.core.file.FileUtil;

public class SettingsBuilder {
  
  private Settings settings;
  private boolean isSizeSet = false;
  
  public SettingsBuilder() {
    settings = new Settings();
  }

  public SettingsBuilder imageSize(int height, int width) {
    settings.setImageHeight(height);
    settings.setImageWidth(width);
    isSizeSet = true;
    return this;
  }
  
  public SettingsBuilder defaultImageId(int defaultImageId) {
    settings.setDefaultImageId(defaultImageId);
    return this;
  }
  
  public SettingsBuilder enableQueryInHashGeneration(boolean enableQueryInHashGeneration) {
    settings.setQueryIncludedInHash(enableQueryInHashGeneration);
    return this;
  }

  public Settings build(Context context){
    settings.setCacheDir(new FileUtil().prepareCacheDirectory(context));
    if(!isSizeSet) {
      setDisplayImageSize(context);
    }
    return settings;
  }
  
  private void setDisplayImageSize(Context context) {
    Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
        .getDefaultDisplay();
    settings.setImageHeight(display.getHeight());
    settings.setImageWidth(display.getWidth());
  }
  
}
