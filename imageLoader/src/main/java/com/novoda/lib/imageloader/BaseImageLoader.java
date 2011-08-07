package com.novoda.lib.imageloader;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.novoda.lib.imageloader.cache.ImageCache;
import com.novoda.lib.imageloader.cache.SoftMapCache;
import com.novoda.lib.imageloader.util.BitmapUtil;
import com.novoda.lib.imageloader.util.FileUtil;

public class BaseImageLoader implements ImageLoader {

	private static final String TAG = "ImageLoader";

	private BitmapUtil bitmapUtil = new BitmapUtil();
	private FileUtil fileUtil;
	private CacheManager photosStack;
	private File cacheDir;
	private int imgHeight;
	private int imgWidth;

	public BaseImageLoader(Context context, int imageWidth, int imageHeight,
			int defaultImageId, String cacheDirName) {
		this.imgHeight = imageHeight;
		this.imgWidth = imageWidth;
		this.fileUtil = new FileUtil(cacheDirName);
		this.photosStack = new CacheManager(this, createCache(),
				bitmapUtil.decodeImageResourceAndScaleBitmap(context,
						defaultImageId, imgWidth, imageHeight));
		this.cacheDir = fileUtil.prepareCacheDir(context);
	}

	@Override
	public void displayImage(String url, Context activity, ImageView imageView) {
		try {
			if (photosStack.hasImageInCache(url)) {
				Bitmap b = photosStack.getImageFromCache(url);
				if (b != null) {
					imageView.setImageBitmap(b);
					return;
				}
			}
			photosStack.push(new CacheManager.Image(url, imageView));
		} catch (Throwable t) {
			Log.e(TAG, t.getMessage(), t);
		}
	}

	@Override
	public Bitmap getBitmap(String url) {
		return getBitmap(url, false);
	}

	@Override
	public Bitmap getBitmap(String url, boolean scale) {
		if (url != null && url.length() != 0) {
			String filename = String.valueOf(url.hashCode());
			File f = new File(cacheDir, filename);
			if (f.exists()) {
				Bitmap b = bitmapUtil.decodeFileAndScale(f, scale, imgWidth,
						imgHeight);
				if (b != null) {
					return b;
				}
			}
			fileUtil.retrieveImage(url, f);
			return bitmapUtil.decodeFileAndScale(f, scale, imgWidth, imgHeight);
		}
		return null;
	}

	@Override
	public void cleanFileCache() {
		fileUtil.deleteDir(cacheDir);
	}

	@Override
	public void cleanCache() {
		photosStack.resetCache(createCache());
	}

	protected ImageCache createCache() {
		return new SoftMapCache();
	}

}
