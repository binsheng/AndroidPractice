package com.dev.bins.cachelib;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by bin on 28/11/2016.
 */

public class MemoryCache {
    LruCache<String, Bitmap> mMemoryLruCache;

    public MemoryCache() {

        long size = Runtime.getRuntime().maxMemory() / 8;
        mMemoryLruCache = new LruCache<String, Bitmap>((int) size) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public Bitmap getBitmapFromMemory(String url){
        return mMemoryLruCache.get(url);
    }

    public void saveBitmapToMemory(String url, Bitmap bitmap) {
        mMemoryLruCache.put(url, bitmap);
    }


}

