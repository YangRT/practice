package com.example.administrator.practice.charter6.build;
//建造者模式：产品类
public class Computer {
    private String mCpu;
    private String mMainboard;
    private String mRam;

    public void setmMainboard(String mMainboard) {
        this.mMainboard = mMainboard;
    }



    public void setmRam(String mRam) {
        this.mRam = mRam;
    }



    public void setmCpu(String mCpu){
        this.mCpu = mCpu;
    }


}
