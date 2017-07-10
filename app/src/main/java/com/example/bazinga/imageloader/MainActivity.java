package com.example.bazinga.imageloader;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView cahceImageView ;

    Button button ;

    String pictureUrl  = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        cahceImageView = (ImageView)findViewById(R.id.cacheImage);
        button         = (Button) findViewById(R.id.loadCachePicture);
        final ImageLoader imageLoader = new ImageLoader();
        imageLoader.displayImage(pictureUrl,cahceImageView);
        // button 用于做缓存是否成功的测试
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLoader.displayImage(pictureUrl,cahceImageView);
            }
        });

    }
}
