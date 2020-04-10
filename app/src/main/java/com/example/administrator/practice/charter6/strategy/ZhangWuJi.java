package com.example.administrator.practice.charter6.strategy;
//策略模式：客户端调用
public class ZhangWuJi {
    public static void main(String[] args){
        Context context;
        //采用较弱对手策略
        context = new Context(new WeakRivalStrategy());
        context.fighting();
    }
}
