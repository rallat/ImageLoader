package com.novoda.lib.imageloader.file;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.util.Log;

public class FileUtil {
	
	private static final String TAG = "ImageLoader";
	
	public void retrieveImage(String url, File f) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new URL(url).openStream();
			os = new FileOutputStream(f);
			copyStream(is, os);
			os.close();
		} catch (FileNotFoundException fnfe) {
			Log.e(TAG, "Unknown Exception while getting the image" + fnfe.getMessage(), fnfe);
		} catch (Exception ex){
			Log.e(TAG, "Unknown Exception while getting the image " + ex.getMessage(), ex);
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
	
	public boolean deleteFileCache(String cacheDirFullPath) {
		return reduceFileCache(cacheDirFullPath, -1);
    }
	
	public boolean reduceFileCache(String cacheDirFullPath, long expirationPeriod) {
		File cacheDir = new File(cacheDirFullPath);
        if (cacheDir.isDirectory()) {
            File[] children = cacheDir.listFiles();
            long lastModifiedThreashold = System.currentTimeMillis() - expirationPeriod;
            for (File f: children) {
            	if(f.lastModified() < lastModifiedThreashold) {
	            	f.delete();
            	}
            }
        }
        return true;
    }
	
}