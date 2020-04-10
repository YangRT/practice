package com.example.administrator.practice.charter6.factory;
//工厂方法模式：具体工厂类
public class GDComputerFactory extends IpComputerFactory {
    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer = null;
        String className = clz.getName();
        try{
            //通过反射来生产不同计算机
            computer = (Computer)Class.forName(className).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T)computer;
    }
}
