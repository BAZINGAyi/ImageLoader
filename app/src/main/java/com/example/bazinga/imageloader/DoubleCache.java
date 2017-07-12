package com.example.bazinga.imageloader;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by bazinga on 2017/7/11.
 *双缓存，获取图片先从内存读取，如果内存中没有缓存，则从 SD 中缓存
 */

public class DoubleCache implements ImageCache{
    String TAG                              =                "DoubleCache";
    MemoryCache mMemoryCache                =                new MemoryCache();
    DiskCache mDiskCache                    =                new DiskCache();

    public Bitmap get(String url){
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    // 将图片缓存到 SD 卡和内存中
    public void put(String url , Bitmap bitmap){
        mDiskCache.put(url,bitmap);
        mMemoryCache.put(url,bitmap);
    }
}
