package com.novoda.imageloader.core;

import java.io.File;

public class Settings {

  private static final long DEFAULT_EXPIRATION_PERIOD = 7l * 24l * 3600l * 1000l;
  private static final boolean DEFAULT_INCLUDE_QUERY_IN_HASH = true;
  
  private File cacheDir;
  
  private long expirationPeriod;
  private int imageHeight;
  private int imageWidth;
  private int defaultImageId;
  private boolean isQueryIncludedInHash;
  
  public Settings() {
    this.expirationPeriod = DEFAULT_EXPIRATION_PERIOD;
    this.setQueryIncludedInHash(DEFAULT_INCLUDE_QUERY_IN_HASH);
  }

  public File getCacheDir() {
    return cacheDir;
  }

  public void setCacheDir(File cacheDir) {
    this.cacheDir = cacheDir;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public void setImageHeight(int imageHeight) {
    this.imageHeight = imageHeight;
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public void setImageWidth(int imageWidth) {
    this.imageWidth = imageWidth;
  }

  public int getDefaultImageId() {
    return defaultImageId;
  }

  public void setDefaultImageId(int defaultImageId) {
    this.defaultImageId = defaultImageId;
  }

  public long getExpirationPeriod() {
    return expirationPeriod;
  }

  public void setExpirationPeriod(long expirationPeriod) {
    this.expirationPeriod = expirationPeriod;
  }

  public boolean isQueryIncludedInHash() {
    return isQueryIncludedInHash;
  }

  public void setQueryIncludedInHash(boolean isQueryIncludedInHash) {
    this.isQueryIncludedInHash = isQueryIncludedInHash;
  }

}
