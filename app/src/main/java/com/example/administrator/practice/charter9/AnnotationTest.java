package com.example.administrator.practice.charter9;

public class AnnotationTest {
    @GET(value = "http://ip.taobao.com/59.108.54.37")
    public String getIpMsg(){
        return "";
    }

    @GET(value = "http://ip.taobao.com/")
    public String getIp(){
        return "";
    }
}
