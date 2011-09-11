package com.novoda.imageloader.core.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.novoda.imageloader.core.file.FileUtil;

public class CacheCleaner extends IntentService {

  public static final String CLEAN_CACHE_ACTION = "com.novoda.lib.imageloader.action.CLEAN_CACHE";
	public static final String EXPIRATION_PERIOD_EXTRA = "com.novoda.lib.imageloader.extra.EXPIRATION_PERIOD";
	public static final String CACHE_DIR_EXTRA = "com.novoda.lib.imageloader.extra.CACHE_DIR";
	
	public static final Intent getCleanCacheIntent(String directoryFullPath, long expirationPeriod) {
		Intent i = new Intent(CLEAN_CACHE_ACTION);
		i.putExtra(EXPIRATION_PERIOD_EXTRA , expirationPeriod);
		i.putExtra(CACHE_DIR_EXTRA , directoryFullPath);
		return i;
	}
	
	public CacheCleaner() {
	  this("CacheCleaner");
	}
	
	public CacheCleaner(String name) {
    super(name);
  }

	@Override
  protected void onHandleIntent(Intent intent) {
	  new CacheCleanerUtil().onReceive(this, intent);    
  }
	
	/*package*/ static class CacheCleanerUtil {
		
		public void onReceive(Context context, Intent intent) {
			if(intent == null) {
				return;
			}
			String action = intent.getAction();
			if(action == null) {
				return;
			}
			if(!intent.hasExtra(CACHE_DIR_EXTRA)) {
				return;
			}
			String cacheDir = intent.getStringExtra(CACHE_DIR_EXTRA);
			if(cacheDir == null || cacheDir.length() == 0) {
				return;
			}
			if(CLEAN_CACHE_ACTION.equals(action)) {
				long exipiredPeriod = intent.getLongExtra(EXPIRATION_PERIOD_EXTRA, -1);
				try {
					new FileUtil().reduceFileCache(cacheDir, exipiredPeriod);
				} catch(Throwable t) {
					//Don't have to fail in case there 
				}
			}
		}
	}

}
