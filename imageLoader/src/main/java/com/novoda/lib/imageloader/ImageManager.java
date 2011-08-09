package com.novoda.lib.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public interface ImageManager {

	void load(String url, Context activity, ImageView imageView);

	Bitmap getBitmap(String url, boolean scale);

	Bitmap getBitmap(String url);

	void deleteFileCache(Context context);

	void cleanCache();

	void reduceFileCache(Context context);

}
