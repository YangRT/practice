package com.example.administrator.practice.charter5;

import java.io.IOException;

import okhttp3.Request;

public abstract class ResultCallBack {
    public abstract void onError(Request request,Exception e);
    public abstract void onResponse(String str) throws IOException;
}
