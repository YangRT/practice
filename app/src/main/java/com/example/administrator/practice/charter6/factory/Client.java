package com.example.administrator.practice.charter6.factory;
//工厂方法模式：客户端
public class Client {
    public static void main(String[] args){
        IpComputerFactory factory = new GDComputerFactory();
        LenovoComputer lenovoComputer = factory.createComputer(LenovoComputer.class);
        lenovoComputer.start();
    }
}
