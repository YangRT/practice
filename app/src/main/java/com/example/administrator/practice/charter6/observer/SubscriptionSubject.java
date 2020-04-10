package com.example.administrator.practice.charter6.observer;

import java.util.ArrayList;
import java.util.List;

//储存订阅公众号的微信用户
public class SubscriptionSubject implements Subject {
    private List<Observer> weixinUserList = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        weixinUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for(Observer observer:weixinUserList){
            observer.update(message);
        }
    }
}
