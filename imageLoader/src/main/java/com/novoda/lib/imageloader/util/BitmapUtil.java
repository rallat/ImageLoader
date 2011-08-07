package com.novoda.lib.imageloader.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class BitmapUtil {
	
	private static final String TAG = "ImageLoader";
	
	public Bitmap decodeFileAndScale(File f, boolean scale, int width, int height){
		try {
			Bitmap unscaledBitmap = BitmapFactory.decodeStream(new FileInputStream(f));
            return scaleBitmap(unscaledBitmap, width, height);
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
	
	public Bitmap decodeImageResourceAndScaleBitmap(Context c, int defaultImageId, int width, int height){
        Bitmap defaultImage = BitmapFactory.decodeResource(c.getResources(), defaultImageId);
        return scaleBitmap(defaultImage, width, height);
	}

}
