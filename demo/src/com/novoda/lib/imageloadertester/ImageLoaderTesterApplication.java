package com.novoda.lib.imageloadertester;

import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

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
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Settings settings = new Settings(this, display.getHeight(), display.getWidth(), R.drawable.bg_img_loading);
        imageLoader = new BaseImageLoader(this, settings);
        //
    }

	//TODO add this to your class
	public static ImageManager getImageLoader() {
		return imageLoader;
	}
}
