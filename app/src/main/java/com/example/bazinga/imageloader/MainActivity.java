package com.example.bazinga.imageloader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bazinga.imageloader.cache.DiskCache;
import com.example.bazinga.imageloader.cache.DoubleCache;
import com.example.bazinga.imageloader.cache.ImageCache;

public class MainActivity extends AppCompatActivity {

    ImageView cacheImageView ;

    Button button ;

    String pictureUrl                    =                "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/" +
                                                          "static/superman/img/logo/bd_logo1_31bdc765.png";
    ImageLoader imageLoader              =                new ImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        cacheImageView = (ImageView)findViewById(R.id.cacheImage);
        button         = (Button) findViewById(R.id.loadCachePicture);

        imageLoader.(new DoubleCache());
        imageLoader.displayImage(pictureUrl,cacheImageView);


    }

    private void click(){
        // button 用于做缓存是否成功的测试
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLoader.displayImage(pictureUrl,cacheImageView);
            }
        });
    }

    /**
     * 用于测试 ImageLoader 的拓展性,实现依赖注入
     * 下面各种的测试方式
     */
    private void testDependencyInjection(){

        imageLoader.setImageCache(new MemoryCache());

        imageLoader.setImageCache(new DiskCache());

        imageLoader.setImageCache(new DoubleCache());

        imageLoader.setImageCache(new ImageCache() {
            @Override
            public void put(String url, Bitmap bitmap) {

            }

            @Override
            public Bitmap get(String url) {
                return null;
            }
        });
    }
}
