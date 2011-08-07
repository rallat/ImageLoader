package com.novoda.imageloadertest;

import android.app.Application;

import com.novoda.lib.imageloader.BaseImageLoader;
import com.novoda.lib.imageloader.ImageLoader;

public class ImageLoaderTesterApplication extends Application {
	
	//TODO add this to your class
	private static ImageLoader imageLoader;
	
	@Override
    public void onCreate() {
        super.onCreate();
        //TODO add this to your class
        imageLoader = new BaseImageLoader(this, 400, 400, R.drawable.bg_img_loading, "ImageLoaderTester");
        //
    }

	//TODO add this to your class
	public static ImageLoader getImageLoader() {
		return imageLoader;
	}
}
