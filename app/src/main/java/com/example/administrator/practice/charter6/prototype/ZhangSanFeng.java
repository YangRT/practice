package com.example.administrator.practice.charter6.prototype;
//模板方法模式：具体实现类
public class ZhangSanFeng extends AbstractSwordman {
    @Override
    protected void weapons() {
        System.out.println("使用太极剑");
    }

    @Override
    protected void neigong() {
        System.out.println("运行纯阳神功。");
    }

    @Override
    protected void moves() {
        System.out.println("使用招式十三剑。");
    }

    @Override
    protected void hook() {
        System.out.println("突然肚子不舒服");
    }
}
