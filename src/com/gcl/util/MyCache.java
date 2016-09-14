package com.gcl.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class MyCache {

	private static LruCache<Integer, Bitmap> lruCache;
	private static Context context;

	public static void init(Context context) {
		MyCache.context = context;
		int MAXMEMONRY = (int) (Runtime.getRuntime().maxMemory() / 1024);
		lruCache = new LruCache<Integer, Bitmap>(MAXMEMONRY / 8) {
			@Override
			protected int sizeOf(Integer key, Bitmap value) {
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};
	}

	public static Bitmap getBtmap(Integer id) {
		Bitmap bitmap = lruCache.get(id);
		if (bitmap != null) {
			return bitmap;
		} else {
			bitmap = BitmapFactory.decodeResource(context.getResources(), id);
			lruCache.put(id, bitmap);
			return bitmap;
		}
	}
}
