package com.example.administrator.practice.charter6.factory;
//简单工厂模式：具体的产品类
public class LenovoComputer extends Computer {
    @Override
    public void start() {
        System.out.println("Lenovo Computer starts");
    }
}
