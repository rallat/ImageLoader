package com.novoda.imageloadertest;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

import com.novoda.lib.imageloader.ImageLoader;

public class ImageLoaderTestActivity extends BaseListActivity {
	
	private static final String[] FROM = new String[] {"url"};
	private static final int[] TO = new int[] {R.id.list_item_image};
	private static final Uri URI = Uri.parse("content://com.novoda.imageloadertest/image");
	
	//TODO add this to your class
	private ImageLoader imageLoader;
	//
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //TODO add this to your class
        imageLoader = ImageLoaderTesterApplication.getImageLoader();
        //
        
        SimpleCursorAdapter adapter = initAdapter();
        ListView lv = getListView();
        ViewBinder binder = getViewBinder();
		if(binder != null) {
			adapter.setViewBinder(binder);			
		}
		lv.setAdapter(adapter);
		
		
		TextView tv = (TextView)findViewById(R.id.new_activity);
		tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ImageLoaderTestActivity.this, ImageLoaderTestActivity.class));
			}
		});
		
		Button b = (Button)findViewById(R.id.clear_cache_btn);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				imageLoader.cleanCache();
			}
		});
		
		Button b1 = (Button)findViewById(R.id.clear_sd_btn);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				imageLoader.cleanFileCache();
			}
		});
    }
    
	private ViewBinder getViewBinder() {
		return new ViewBinder() {
			@Override
			public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
				try {
					((ImageView) view).setTag(cursor.getString(columnIndex));
					
					//TODO add this to your class
		            imageLoader.displayImage(cursor.getString(columnIndex), 
		            		ImageLoaderTestActivity.this, (ImageView) view);
		            //
				} catch(Exception e) {
					Log.e("ImageLoader", "exception : " + e.getMessage());
				}
	            return true;
			}
		};
	}

	private SimpleCursorAdapter initAdapter() {
		return new SimpleCursorAdapter(this, R.layout.image_item, getCursor(), FROM, TO);
	}

	private Cursor getCursor() {
		return managedQuery(URI, null, null, null, null);
	}
    
}