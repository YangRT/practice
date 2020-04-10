package com.example.administrator.practice.charter6.strategy;
//策略模式：具体策略实现
public class StrongRivalStrategy implements FightingStrategy {
    @Override
    public void fighting() {
        System.out.println("遇到强大对手，张无忌使用乾坤大挪移。");
    }
}
