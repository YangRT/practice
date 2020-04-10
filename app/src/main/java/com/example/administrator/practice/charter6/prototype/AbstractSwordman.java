package com.example.administrator.practice.charter6.prototype;
//模板方法模式：抽象类
public abstract class AbstractSwordman {

    public final void fighting(){
        neigong();
        meridian();
        if(hasWeapons()){
            weapons();
        }
        moves();
        hook();
    }

    protected void hook(){}
    protected abstract void weapons();
    protected abstract void neigong();
    protected abstract void moves();
    protected void meridian(){
        System.out.println("开启正经与奇经");
    }

    //钩子方法
    protected boolean hasWeapons(){
        return true;
    }
}
