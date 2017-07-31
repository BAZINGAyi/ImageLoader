package com.example.bazinga.imageloader.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by bazinga on 2017/7/31.
 */

public class CloseUtils {

    private CloseUtils(){

    }

    /**
     * 关闭 closeable 对象
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable){
        if(null != closeable){
            try{
                closeable.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
