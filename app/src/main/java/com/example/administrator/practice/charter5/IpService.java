package com.example.administrator.practice.charter5;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IpService {
    @GET("getIpInfo.php?ip=59.108.54.37")
    Call<IpModel> getIpMsg();
    //动态配置URL地址
    @GET("{path}/getIpInfo.php?ip=59.108.54.37")
    Call<IpModel> getIpMsg(@Path("path")String path);
    //动态指定查询条件
    @GET("getIpInfo.php")
    Call<IpModel> getIpMsgs(@Query("ip")String ip);
    //动态指定查询条件组
    @GET("getIpInfo.php")
    Call<IpModel> getIpMsgs(@QueryMap Map<String,String> options);

    //POST请求

    //@FormUrlEncoded标明这是一个表单请求，用@Filed标示所对应String类型数据的键
    @FormUrlEncoded
    @POST("getIpInfo.php")
    Call<IpModel> getIpInfo(@Field("ip") String first);

    //@Body标识参数对象，Retrofit会将Ip对象转换为Json字符串.
    @POST("getIpInfo.php")
    Call<IpModel> getIpInfo(@Body Ip ip);


    //静态添加消息报头
    @GET("some/endpoint")
    @Headers("Accept-Encoding:application/json")
    Call<IpModel> getCarType();

    //多个报头可以用{}包含起来
    @GET("some/endpoint")
    @Headers({"Accept-Encoding:application/json",
                "User-Agent:MoonRetrofit"})
    Call<IpModel> getCarTypes();

    //动态添加
    @GET("some/endpoint")
    Call<IpModel> getCarType(@Header("Loction")String location);
}
