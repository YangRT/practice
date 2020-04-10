package com.example.administrator.practice.charter6.strategy;
//策略模式：具体策略实现
public class CommonRivalStrategy implements FightingStrategy {
    @Override
    public void fighting() {
        System.out.println("遇到普通对手，张无忌使用圣火令神功。");
    }
}
