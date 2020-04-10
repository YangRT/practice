package com.example.administrator.practice.charter6.build;
//客户端调用导演类
public class CreateComputer {
    public static void main(String[] args){
        Builder mBuilder = new MoonComputerBuilder();
        Director mDirector = new Director(mBuilder);
        mDirector.createComputer("i7-6700","华擎玩家至尊","三星 DDR4");
    }
}
