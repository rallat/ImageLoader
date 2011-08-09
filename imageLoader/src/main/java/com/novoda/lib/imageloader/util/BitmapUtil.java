package com.novoda.lib.imageloader.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.novoda.lib.imageloader.Settings;

public class BitmapUtil {
	
	private static final String TAG = "ImageLoader";
	
	public Bitmap decodeFileAndScale(File f, boolean scale, Settings settings){
		try {
			Bitmap unscaledBitmap = BitmapFactory.decodeStream(new FileInputStream(f));
            return scaleBitmap(unscaledBitmap, settings.getImageWidth(), settings.getImageHeight());
		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return null;
	}
	
	public Bitmap scaleBitmap(Bitmap b, int width, int height){
		int imageHeight = b.getHeight();
		int imageWidth = b.getWidth();
		int finalWidth = width;
		int finalHeight = height;
		if(imageHeight > imageWidth) {
			float factor = ((float)height)/((float)imageHeight);
			finalHeight = new Float(imageHeight*factor).intValue();
			finalWidth = new Float(imageWidth*factor).intValue();
		} else {
			float factor = ((float)width)/((float)imageWidth);
			finalHeight = new Float(imageHeight*factor).intValue();
			finalWidth = new Float(imageWidth*factor).intValue();
		}
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, finalWidth, finalHeight, true);
        b.recycle();
        return scaledBitmap;
	}
	
	public Bitmap decodeImageResourceAndScaleBitmap(Settings settings){
        return scaleBitmap(settings.getDefaultBitmap(), settings.getImageWidth(), settings.getImageHeight());
	}

}
