package com.novoda.lib.imageloadertester;

import android.app.Application;

import com.novoda.lib.imageloader.BaseImageLoader;
import com.novoda.lib.imageloader.ImageManager;
import com.novoda.lib.imageloader.Settings;

public class ImageLoaderTesterApplication extends Application {
	
	//TODO add this to your class
	private static ImageManager imageLoader;
	
	@Override
    public void onCreate() {
        super.onCreate();
        //TODO add this to your classs
        Settings settings = new Settings(this, 150, 150, R.drawable.bg_img_loading);
        imageLoader = new BaseImageLoader(settings);
        //
    }

	//TODO add this to your class
	public static ImageManager getImageLoader() {
		return imageLoader;
	}
}
