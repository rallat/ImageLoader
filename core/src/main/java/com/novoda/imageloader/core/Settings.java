package com.novoda.imageloader.core;

import java.io.File;
import java.lang.ref.SoftReference;

import com.novoda.imageloader.core.file.FileUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Settings {

  // It is better to consider period greater than 1500 millisec
  private static final long EXPIRATION_PERIOD_DEFAULT = 7l * 24l * 3600l * 1000l;
  private static final String DEFAULT_NAME = "imagedata";
  private File cacheDir;
  private File phoneCacheDir;
  //String packageName = context.getApplicationContext().getPackageName();
  //android.os.Environment.getExternalStorageDirectory()
  private int imageHeight;
  private int imageWidth;
  private int defaultImageId;
  private long expirationPeriod;
  private String relativePath;
  private SoftReference<Bitmap> defaultBitmap;

  public Settings(FileUtil fileUtil, File phoneCacheDir, String relativePath, int imageHeight, int imageWidth, int defaultImageId,
      long fileCacheExpirationPeriod) {
    this.imageHeight = imageHeight;
    this.imageWidth = imageWidth;
    this.expirationPeriod = fileCacheExpirationPeriod;
    this.defaultImageId = defaultImageId;
    this.relativePath = relativePath;
    this.cacheDir = fileUtil.prepareCacheDir(phoneCacheDir, relativePath);
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public File getCacheDir() {
    return cacheDir;
  }

  public long getExpirationPeriod() {
    return expirationPeriod;
  }

  public Bitmap getDefaultBitmap(Context context) {
    if (defaultBitmap != null) {
      return defaultBitmap.get();
    }
    Bitmap b = BitmapFactory.decodeResource(context.getResources(), defaultImageId);
    defaultBitmap = new SoftReference<Bitmap>(b);
    return b;
  }



}
