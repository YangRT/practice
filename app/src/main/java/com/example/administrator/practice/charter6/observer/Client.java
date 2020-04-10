package com.example.administrator.practice.charter6.observer;
//观察者模式：客户端调用
public class Client {
    public static void main(String[] args){
        SubscriptionSubject subscriptionSubject = new SubscriptionSubject();
        //创建微信用户
        WeiXinUser user = new WeiXinUser("1");
        WeiXinUser user2 = new WeiXinUser("2");
        WeiXinUser user3 = new WeiXinUser("3");
        //订阅公众号
        subscriptionSubject.attach(user);
        subscriptionSubject.attach(user2);
        subscriptionSubject.attach(user3);
        //公众号发新信息给用户
        subscriptionSubject.notify("777");
    }
}
