package com.example.administrator.practice.charter6.agency;

public class Purchasing implements IShop {
    private IShop mShop;

    public Purchasing(IShop shop){
        mShop = shop;
    }

    @Override
    public void buy() {
        mShop.buy();
    }
}
