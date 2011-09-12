package com.novoda.imageloader.demo.provider;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

	private Context context;

	public DatabaseManager(Context context) {
		super(context, "com.novoda.imageloaderdemo", null, 13);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		create(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		drop(db);
		onCreate(db);
	}

	private void create(SQLiteDatabase db) {
		List<String> stms = new ArrayList<String>();
		stms.add("create table if not exists image(_id integer primary key autoincrement, " +
			"url text);");
		AssetManager mngr = context.getAssets();
		try {
			stms.addAll(SqlFile.statementsFrom(new InputStreamReader(mngr
					.open("contents/images.sql"))));
		} catch (Exception e) {
			Log.e("Exception", "Error while Inserting editions", e);
		}
		
		exec(db, stms);
	}

	private void drop(SQLiteDatabase db) {
		List<String> stms = new ArrayList<String>();
		stms.add("drop table if exists image;");
		exec(db, stms);
	}

	private static final void exec(SQLiteDatabase db, List<String> staments) {
		for (String stm : staments) {
			Log.v("exec", stm);
			try {
				db.execSQL(stm);
			} catch (RuntimeException re) {
				Log.e("exec", "RuntimeException", re);
			}
		}
	}

}
