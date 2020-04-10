package com.example.administrator.practice.charter5;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpEngine {
    private static volatile OkHttpEngine mInstance;
    private OkHttpClient mOkHttpClient;
    private android.os.Handler mHandler;

    public static OkHttpEngine getInstance(Context context){
        if(mInstance == null){
            synchronized (OkHttpEngine.class){
                if(mInstance == null){
                    mInstance = new OkHttpEngine(context);
                }
            }
        }
        return mInstance;
    }

    private OkHttpEngine(Context context){
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        mOkHttpClient = new OkHttpClient.Builder()
                                    .connectTimeout(15,TimeUnit.SECONDS)
                                        .writeTimeout(20,TimeUnit.SECONDS)
                                            .readTimeout(20,TimeUnit.SECONDS)
                                                .cache(new Cache(sdcache.getAbsoluteFile(),cacheSize))
                                                        .build();
        mHandler = new android.os.Handler();
    }

    public void getAsynHttp(String url,ResultCallBack callBack){
        final Request request = new Request.Builder().url(url).build();
        Call call =mOkHttpClient.newCall(request);
        dealResult(call,callBack);
    }

    private void dealResult(Call call, final ResultCallBack callBack){
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallback(call.request(),e,callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSucessCallback(response.body().string(),callBack);
            }
        });
    }

    private void sendSucessCallback(final String str,final ResultCallBack callBack){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callBack != null){
                    try{
                        callBack.onResponse(str);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void sendFailedCallback(final Request request,final Exception e,final ResultCallBack callBack){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callBack != null){
                    callBack.onError(request,e);
                }
            }
        });
    }
}
