package com.dev.bins.cachelib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by bin on 29/11/2016.
 */

public class NetCache {

    private MemoryCache mMemoryCache;
    private LocalCache mLocalCache;

    public NetCache(MemoryCache mMemoryCache, LocalCache mLocalCache) {
        this.mMemoryCache = mMemoryCache;
        this.mLocalCache = mLocalCache;
    }


    public Bitmap getBitmap(final String url) {
        Bitmap bitmap = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                mMemoryCache.saveBitmap(url, bitmap);
                mLocalCache.saveBitmap(url, response.body().byteStream());
            }
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
