package com.dev.bins.cachelib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.R.attr.key;

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

    public void saveBitmap(String url, InputStream in) {
        String key = MD5(url);
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            OutputStream outputStream = editor.newOutputStream(0);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            byte[] buffer = new byte[1024];
            while (bufferedInputStream.read(buffer) != -1) {
                bufferedOutputStream.write(buffer);
            }
            editor.commit();
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmap(String url) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(MD5(url));
            InputStream inputStream = snapshot.getInputStream(0);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    public String MD5(String url) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(url.getBytes());
            return bytesToHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return String.valueOf(url.hashCode());
        }
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


}
