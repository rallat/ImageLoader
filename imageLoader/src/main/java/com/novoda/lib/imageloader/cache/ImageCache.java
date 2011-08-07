package com.novoda.lib.imageloader.cache;

import android.graphics.Bitmap;

public interface ImageCache {

	boolean hasImage(String url);

	Bitmap get(String url);

	void put(String url, Bitmap bmp);

	void clean();

}
