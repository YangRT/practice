package com.example.administrator.practice.charter6.singleton;

public class Singleton3 {
    //静态内部类模式
    private Singleton3(){}

    public static Singleton3 getInstance(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        private static final Singleton3 instance = new Singleton3();
    }
}
