package com.novoda.lib.imageloader.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.content.Context;
import android.util.Log;

public class FileUtil {
	
	private static final String TAG = "ImageLoader";
	private String cacheDirPath;
	
	public FileUtil(String cacheDirPath) {
		this.cacheDirPath = cacheDirPath;
	}
	

	public void retrieveImage(String url, File f) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new URL(url).openStream();
			os = new FileOutputStream(f);
			copyStream(is, os);
			os.close();
		} catch (FileNotFoundException fnfe) {
			Log.e(TAG, "Unknown Exception while getting the image" + fnfe.getMessage());
		} catch (Exception ex){
			Log.e(TAG, "Unknown Exception while getting the image " + ex.getMessage());
		} finally {
			closeSilently(is);
			closeSilently(os);
		}
	}
	
	public void closeSilently(Closeable c) {
		try {
			if(c != null) {
				c.close();
			}
		} catch (Exception e) {
			Log.e(TAG, "Problem closing stream " + e.getMessage());
		}
	}
	
	public void copyStream(InputStream is, OutputStream os)	{
		final int bufferSize = 1024;
		try {
			byte[] bytes = new byte[bufferSize];
			for(;;){
				int count = is.read(bytes, 0, bufferSize);
				if(count == -1) {
					break;
				}
				os.write(bytes, 0, count);
			}
		} catch(Exception ex){
			Log.e(TAG, "Exception : ", ex);
		}
	}
	
	public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
            	File f = new File(dir, children[i]);
            	f.delete();
            }
        }
        return true;
    }
	
	public File prepareCacheDir(Context context) {
		File cacheDir = null;
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
			cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), cacheDirPath);
		} else{
			cacheDir = context.getCacheDir();
		}
		if(!cacheDir.exists()){
			cacheDir.mkdir();
		}
		return cacheDir;
	}
	
}
