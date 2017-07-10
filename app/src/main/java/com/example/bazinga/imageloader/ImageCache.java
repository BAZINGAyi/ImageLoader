package com.example.bazinga.imageloader;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v4.util.LruCache;


/**
 * Created by bazinga on 2017/7/10.
 */

public class ImageCache {
    // 图片缓存
    LruCache<String,Bitmap> mImageCache;

    public ImageCache(){
        initImageCache();
    }

    private void initImageCache(){

        // 计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 取四分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }

        };
    }

    public void put(String url, Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }

    public Bitmap get(String url){
        return mImageCache.get(url);
    }
}
