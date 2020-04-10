package com.example.administrator.practice.charter5;

import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetTask {
    public static void getData(){
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder()
                                         .baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        IpService ipService = retrofit.create(IpService.class);
        Call<IpModel> call = ipService.getIpMsg();
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                String country = response.body().getData().getCountry();
                Log.d("NetTask",country);
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {

            }
        });
    }
}
