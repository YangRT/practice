package com.example.administrator.practice.charter6.decorate;
//客户端调用
public class Client {
    public static void main(String[] args){
        Man man = new Man();
        J j = new J(man);
        j.attackMagic();
    }

}
