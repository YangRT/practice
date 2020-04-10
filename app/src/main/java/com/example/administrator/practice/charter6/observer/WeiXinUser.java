package com.example.administrator.practice.charter6.observer;
//观察者模式：具体观察者
public class WeiXinUser implements Observer {
    private  String name; // 微信用户名

    public WeiXinUser(String name){
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name+"-"+message);
    }
}
