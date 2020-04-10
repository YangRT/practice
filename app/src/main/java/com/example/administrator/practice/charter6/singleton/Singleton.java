package com.example.administrator.practice.charter6.singleton;
//饿汉模式
public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}
