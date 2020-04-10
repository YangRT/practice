package com.example.administrator.practice;

import android.app.Application;
import android.content.Context;

import com.example.administrator.practice.plugin.LoadPluginUtil;

public class MyApplication extends Application {

    private static String CONFIG = "config";

    // 获取到主线程的上下文
    private static MyApplication mContext = null;
    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        context = this.getApplicationContext();
        LoadPluginUtil.loadClass(context);
    }

    public static MyApplication getApplication() {
        return mContext;
    }
}
