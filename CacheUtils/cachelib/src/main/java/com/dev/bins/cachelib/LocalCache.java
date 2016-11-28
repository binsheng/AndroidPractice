package com.dev.bins.cachelib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

import static com.jakewharton.disklrucache.DiskLruCache.open;

/**
 * Created by bin on 28/11/2016.
 */

public class LocalCache {
    private static LocalCache instance;
    private String cachePath;
    private long size = 10 * 1024 * 1024;
    private DiskLruCache mDiskLruCache;

    private LocalCache(Context context, String type) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath() + File.separator;
        } else {
            cachePath = context.getCacheDir().getPath() + File.separator;
        }
        cachePath += type;
        int version = getAppVersion(context);
        File file = new File(cachePath);
        if (!file.exists()) file.mkdirs();
        try {
            mDiskLruCache = DiskLruCache.open(file, version, 1, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LocalCache getInstance(Context context, String type) {
        if (instance == null) {
            instance = new LocalCache(context, type);
        }
        return instance;
    }

    private int getAppVersion(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
