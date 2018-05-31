package com.example.ffmpegsdlplayer.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by huxin on 2017/2/28.
 */

public class MyApplication extends Application{
    private static MyApplication application;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();//VM严格模式
            StrictMode.setVmPolicy(builder.build());
        }
    }

    public static MyApplication getApplication() {
        return application;
    }

    public static Context getContext() {
        return context;
    }
}
