package com.example.administrator.practice.charter6.factory;
//工厂方法模式：抽象工厂类
public abstract class IpComputerFactory {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}
