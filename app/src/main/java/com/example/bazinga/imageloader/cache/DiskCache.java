package com.example.bazinga.imageloader.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.bazinga.imageloader.utils.CloseUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by bazinga on 2017/7/11.
 */

public class DiskCache implements ImageCache{
    static String cacheDir                 =             "sdcard/cache/";
    static String TAG                      =             "DiskCache";
    //从 SD 缓存中获取图片
    public Bitmap get(String url){
        Log.e(TAG, cacheDir + url );
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    //将图片缓存到 SD 中
    public void put(String url, Bitmap bitmap){

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }

}
