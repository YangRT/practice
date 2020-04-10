package com.example.administrator.practice.charter6.factory;
//简单工厂类：客户端调用工厂类
public class CreateComputer {
    public static void main(String[] args){
        ComputerFactory.createComputer("hp").start();
    }
}
