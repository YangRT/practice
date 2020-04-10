package com.example.administrator.practice.charter6.decorate;
//抽象装饰者
public abstract class Master extends Swordsman {
    private Swordsman mSwordsman;

    public Master(Swordsman swordsman){
        this.mSwordsman = swordsman;
    }

    @Override
    public void attackMagic() {
        mSwordsman.attackMagic();
    }
}
