package com.novoda.imageloadertest.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class ImageLoaderTestProvider extends ContentProvider {

	private CustomUriMatcher matcher = new CustomUriMatcher();
	private DatabaseManager databaseManager;
	private SQLiteDatabase database;
	
	protected SQLiteDatabase getDataBase() {
        if (database == null) {
            databaseManager = new DatabaseManager(getContext());
            database = databaseManager.getWritableDatabase();
        }
        return database;
    }
	
	@Override
	public String getType(Uri uri) {
		String type = null;
        switch (matcher.match(uri)) {
            case CustomUriMatcher.IMAGE_INCOMING_COLLECTION: {
                type = CustomUriMatcher.IMAGE_COLLECTION_TYPE;
                break;
            }
            default: {
            	Log.e("ImageLoader", "Problem with query, not Implemented for : " + uri);
                throw new RuntimeException("Problem with query, not Implemented for : " + uri);
            }
        }
            return type;
    	}
	
	@Override
	public boolean onCreate() {
		return false;
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		return null;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor cursor;
        switch (matcher.match(uri)) {
            case CustomUriMatcher.IMAGE_INCOMING_COLLECTION: {
                cursor = getDataBase().query("image", projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            }
            default: {
            	Log.e("ImageLoader", "Problem with query, not Implemented for : " + uri);
                throw new RuntimeException("Problem with query, not Implemented for : " + uri);
            }
        }
        return cursor;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
