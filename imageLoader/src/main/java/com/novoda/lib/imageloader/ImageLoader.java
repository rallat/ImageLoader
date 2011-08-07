package com.novoda.lib.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public interface ImageLoader {

	void displayImage(String url, Context activity, ImageView imageView);

	Bitmap getBitmap(String url, boolean scale);

	Bitmap getBitmap(String url);

	void cleanFileCache();

	void cleanCache();

}
