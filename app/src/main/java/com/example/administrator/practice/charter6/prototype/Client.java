package com.example.administrator.practice.charter6.prototype;
//模板方法模式：客户端调用
public class Client {

    public static void main(String[] args){
        ZhangWuJi zhangWuJi = new ZhangWuJi();
        zhangWuJi.fighting();
        ZhangSanFeng zhangSanFeng = new ZhangSanFeng();
        zhangSanFeng.fighting();
    }
}
