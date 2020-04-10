package com.example.administrator.practice.charter6.singleton;

public class Singleton2 {
    //双重检查模式
    private static volatile Singleton2 instance;
    private Singleton2(){}

    private static Singleton2 getInstance(){
        if(instance == null){
            synchronized (Singleton2.class){
                if(instance == null){
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
