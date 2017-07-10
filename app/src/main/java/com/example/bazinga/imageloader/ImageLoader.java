package com.example.bazinga.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.widget.HeterogeneousExpandableList;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by p_zhywzhang on 2017/7/7.
 */

public class ImageLoader {
    // 图片缓存
    ImageCache                             mImageCache           =        new ImageCache();
    // 线程池，线程数量为 CPU 数量
    ExecutorService                        mExecutorService      =        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    String TAG = "ImageLoader" ;

    public void displayImage(final String url, final ImageView imageView){

        Bitmap bitmap = mImageCache.get(url);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
            Log.e(TAG, "从内存中读出");
            return;
        }

        imageView.setTag(url);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "开启了网络的请求");
                Bitmap bitmap = downLoad(url);
                if (bitmap == null){
                    return;
                }

                if(imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }

                mImageCache.put(url,bitmap);

            }
        });
    }

    private Bitmap downLoad(String imageUrl) {

        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  bitmap;
    }

}
