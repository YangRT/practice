package com.example.administrator.practice.charter6.singleton;
//懒汉模式（线程不安全)
public class Singleton1 {
    private static Singleton1 instance;
    private Singleton1(){}
    //用synchronized关键字修饰方法，可保证线程安全
    private static Singleton1 getInstance(){
        if(instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}
