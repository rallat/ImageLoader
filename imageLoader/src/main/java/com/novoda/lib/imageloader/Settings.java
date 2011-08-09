package com.novoda.lib.imageloader;

import java.io.File;
import java.lang.ref.SoftReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Settings {
	
	//It is better to consider period greater than 1500 millisec
	public static final long EXPIRATION_PERIOD = 7l*24l*3600l*1000l;
	private static final String DEFAULT_NAME = "imagedata";
	private Context context;
	private File cacheDir;
	private int imageHeight;
	private int imageWidth;
	private int defaultImageId;
	private SoftReference<Bitmap> defaultBitmap;
	
	public Settings(Context context) {
		this(context, 500, 500, -1);
	}
	
	public Settings(Context context, int imageHeight, int imageWidth, int defaultImageId) {
		this.context = context;
		this.cacheDir = prepareCacheDir(context);
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.defaultImageId = defaultImageId;
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
		return EXPIRATION_PERIOD;
	}
	
	public Bitmap getDefaultBitmap() {
		if(defaultBitmap != null) {
			return defaultBitmap.get();
		}
		Bitmap b = BitmapFactory.decodeResource(context.getResources(), defaultImageId);
		defaultBitmap = new SoftReference<Bitmap>(b);
		return b; 
	}

	public Context getContext() {
		return context;
	}
	
	private boolean isMounted() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
			return true;
		}
		return false;
	}
	
	private File getExternalStorageDirectory() {
		return android.os.Environment.getExternalStorageDirectory();
	}

	private File getSystemCacheDir() {
		return context.getCacheDir();
	}
	
	private String getDefaultPath(Context context) {
		String packageName = context.getApplicationContext().getPackageName();
		return packageName + "/" + DEFAULT_NAME;
	}
	
	private File prepareCacheDir(Context context) {
		File cacheDir = null;
		if (isMounted()){
			cacheDir = new File(getExternalStorageDirectory(), getDefaultPath(context));
		} else{
			cacheDir = getSystemCacheDir();
		}
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}
	
}
