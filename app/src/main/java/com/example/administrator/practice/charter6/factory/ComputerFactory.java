package com.example.administrator.practice.charter6.factory;
//简单工厂模式：工厂类
public class ComputerFactory {
    public static Computer createComputer(String type){
        Computer mComputer = null;
        switch (type){
            case "lenovo":
                mComputer = new LenovoComputer();
                break;
            case "hp":
                mComputer = new HpComputer();
                break;
        }
        return mComputer;
    }
}
