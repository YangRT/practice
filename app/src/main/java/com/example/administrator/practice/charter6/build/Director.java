package com.example.administrator.practice.charter6.build;
//用导演类来统一组装过程
public class Director {
    Builder mBuilder = null;
    public Director(Builder mBuilder){
        this.mBuilder = mBuilder;
    }

    public Computer createComputer(String cpu,String mainboard,String ram){
        mBuilder.buildCpu(cpu);
        mBuilder.buildMainboard(mainboard);
        mBuilder.buildRam(ram);
        return mBuilder.create();
    }

}
