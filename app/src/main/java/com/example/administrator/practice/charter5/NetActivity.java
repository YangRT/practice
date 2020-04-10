package com.example.administrator.practice.charter5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.practice.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                                      .url("http://blog.csdn.net/itachi85")
                                      .method("GET",null)
                                      .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d("NetActivity",str);
            }
        });

        RequestBody body = new FormBody.Builder().add("ip","59.108.54.37").build();
        Request request1 = new Request.Builder().url("http://ip.taobao.com/service/getIpInfo.php")
                                                .post(body)
                                                .build();
        OkHttpClient mClient = new OkHttpClient();
        mClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d("NetActivity",str);
            }
        });
    }
}
