package com.example.bazinga.imageloader.cache;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v4.util.LruCache;


/**
 * Created by bazinga on 2017/7/10.
 * key 为图片的 url ，所有的缓存形式都实现该接口
 */

public interface ImageCache {

    public void put(String url, Bitmap bitmap);

    public Bitmap get(String url);
}
