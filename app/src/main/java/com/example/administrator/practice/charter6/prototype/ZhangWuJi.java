package com.example.administrator.practice.charter6.prototype;
//模板方法模式：具体实现类
public class ZhangWuJi extends AbstractSwordman {
    @Override
    protected void weapons() {

    }

    @Override
    protected boolean hasWeapons() {
        return false;
    }

    @Override
    protected void neigong() {
        System.out.println("运行九阳神功。");
    }

    @Override
    protected void moves() {
        System.out.println("使用乾坤大挪移。");

    }
}
