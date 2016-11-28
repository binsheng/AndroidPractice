package com.dev.bins.cachelib;

/**
 * Created by bin on 28/11/2016.
 */

public class CacheManager {

    private static CacheManager instance;


    private CacheManager(){

    }

    public static CacheManager getInstance() {
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }
}
