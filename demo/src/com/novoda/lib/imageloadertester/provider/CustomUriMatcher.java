package com.novoda.lib.imageloadertester.provider;

import android.content.UriMatcher;
import android.net.Uri;

public class CustomUriMatcher extends UriMatcher {

	public static final String IMAGE_ITEM_TYPE = "vnd.android.cursor.item/vnd.imageloadertest.image";
	public static final String IMAGE_COLLECTION_TYPE = "vnd.android.cursor.dir/vnd.imageloadertest.image";
	public static final int IMAGE_INCOMING_ITEM = 10;
	public static final int IMAGE_INCOMING_COLLECTION = 20;

	public CustomUriMatcher(int code) {
		super(code);
		setUp();
	}

	public CustomUriMatcher() {
		super(UriMatcher.NO_MATCH);
		setUp();
	}

	public void setUp() {
		add("image", IMAGE_INCOMING_COLLECTION);
		add("image" + "/#", IMAGE_INCOMING_ITEM);
	}

	public void add(String path, int code) {
		super.addURI("com.novoda.imageloadertest", path, code);
	}

	public static final String[] getIdSelectionArgumentsFromUri(Uri uri) {
		return new String[] { uri.getPathSegments().get(1) };
	}
}
