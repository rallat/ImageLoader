package com.novoda.lib.imageloader.cache;

import android.graphics.Bitmap;

public class NoCache implements ImageCache {

	@Override
	public boolean hasImage(String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bitmap get(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(String url, Bitmap bmp) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void clean() {
		// TODO
	}

}
