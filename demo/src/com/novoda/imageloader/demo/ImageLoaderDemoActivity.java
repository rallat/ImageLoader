package com.novoda.imageloader.demo;

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

import com.novoda.imageloader.core.ImageManager;

public class ImageLoaderDemoActivity extends BaseListActivity {

  private static final String[] FROM = new String[] { "url" };
  private static final int[] TO = new int[] { R.id.list_item_image };
  private static final Uri URI = Uri.parse("content://com.novoda.imageloadertest/image");

  // TODO add this to your class
  private ImageManager imageLoader;
  //

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    // TODO add this to your class
    imageLoader = ImageLoaderDemoApplication.getImageLoader();
    //
    setAdapter();
    setNewActivityButton();
    setClearCacheButton();
    setClearFileCache();
    setReduceFileCache();
  }

  private ViewBinder getViewBinder() {
    return new ViewBinder() {
      @Override
      public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        try {
          ((ImageView) view).setTag(cursor.getString(columnIndex));
          // TODO add this to your class
          imageLoader.load(cursor.getString(columnIndex), ImageLoaderDemoActivity.this,
              (ImageView) view);
          //
        } catch (Exception e) {
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

  private void setAdapter() {
    SimpleCursorAdapter adapter = initAdapter();
    ListView lv = getListView();
    ViewBinder binder = getViewBinder();
    if (binder != null) {
      adapter.setViewBinder(binder);
    }
    lv.setAdapter(adapter);
  }

  private void setReduceFileCache() {
    Button reduceFileCache = (Button) findViewById(R.id.reduce_sd_btn);
    reduceFileCache.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        imageLoader.reduceFileCache(getApplicationContext());
      }
    });
  }

  private void setClearFileCache() {
    Button clearFileCache = (Button) findViewById(R.id.clear_sd_btn);
    clearFileCache.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        imageLoader.deleteFileCache(getApplicationContext());
      }
    });
  }

  private void setClearCacheButton() {
    Button clearCache = (Button) findViewById(R.id.clear_cache_btn);
    clearCache.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        imageLoader.cleanCache();
      }
    });
  }

  private void setNewActivityButton() {
    Button newActivity = (Button) findViewById(R.id.new_activity_btn);
    newActivity.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(ImageLoaderDemoActivity.this, ImageLoaderDemoActivity.class));
      }
    });
  }

}