package com.dev.bins.cachelib;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by bin on 28/11/2016.
 */

public class CacheManager {

    private static CacheManager instance;
    private MemoryCache mMemoryCache;
    private LocalCache mLocalCache;
    private NetCache mNetCache;

    private CacheManager(Context context) {
        mMemoryCache = new MemoryCache();
        mLocalCache = new LocalCache(context, "img");
        mNetCache = new NetCache(mMemoryCache, mLocalCache);
    }

    public static CacheManager getInstance(Context context) {
        if (instance == null) {
            instance = new CacheManager(context);
        }
        return instance;
    }

    public void displayImage(ImageView iv, String url) {
        Bitmap bitmap;
        bitmap = mMemoryCache.getBitmap(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            return;
        }

        bitmap = mLocalCache.getBitmap(url);
        if (bitmap != null) {
            return;
        }

        bitmap = mNetCache.getBitmap(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        }
    }
}
