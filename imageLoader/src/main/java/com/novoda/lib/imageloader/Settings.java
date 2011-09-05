package com.novoda.lib.imageloader;

import java.io.File;
import java.lang.ref.SoftReference;

import com.novoda.lib.imageloader.file.FileUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Settings {
	
	//It is better to consider period greater than 1500 millisec
	private static final long EXPIRATION_PERIOD_DEFAULT = 7l*24l*3600l*1000l;
	private static final String DEFAULT_NAME = "imagedata";
	private File cacheDir;
	private int imageHeight;
	private int imageWidth;
	private int defaultImageId;
	private long expirationPeriod;
	private SoftReference<Bitmap> defaultBitmap;
	
	public Settings(Context context) {
		this(context, 500, 500, -1, EXPIRATION_PERIOD_DEFAULT);
	}
	
	public Settings(Context context, int imageHeight, int imageWidth, int defaultImageId) {
		this(context, imageHeight, imageWidth, defaultImageId, EXPIRATION_PERIOD_DEFAULT);
	}
	
	public Settings(Context context, int imageHeight, int imageWidth, int defaultImageId, long fileCacheExpirationPeriod) {
		this.cacheDir = prepareCacheDir(context);
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.expirationPeriod = fileCacheExpirationPeriod;
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
		return expirationPeriod;
	}
	
	public Bitmap getDefaultBitmap(Context context) {
		if(defaultBitmap != null) {
			return defaultBitmap.get();
		}
		Bitmap b = BitmapFactory.decodeResource(context.getResources(), defaultImageId);
		defaultBitmap = new SoftReference<Bitmap>(b);
		return b; 
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
	
	private String getDefaultPath(Context context) {
		String packageName = context.getApplicationContext().getPackageName();
		return packageName + "/" + DEFAULT_NAME;
	}
	
	private File prepareCacheDir(Context context) {
		File cacheDir = null;
		if (isMounted()){
			cacheDir = new File(getExternalStorageDirectory(), getDefaultPath(context));
		} else{
			cacheDir = context.getCacheDir();
		}
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
			new FileUtil().createNomediaFile(cacheDir);
		}
		return cacheDir;
	}
	
}
