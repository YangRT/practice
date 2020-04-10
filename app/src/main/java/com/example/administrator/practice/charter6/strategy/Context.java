package com.example.administrator.practice.charter6.strategy;
//策略模式，上下文角色
public class Context {
    private FightingStrategy fightingStrategy;
    public Context(FightingStrategy fightingStrategy){
        this.fightingStrategy = fightingStrategy;
    }

    public void fighting(){
        fightingStrategy.fighting();
    }
}
