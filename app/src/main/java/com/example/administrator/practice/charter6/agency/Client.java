package com.example.administrator.practice.charter6.agency;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args){
        IShop man = new Purchaser();
        IShop purchasing = new Purchasing(man);
        purchasing.buy();
        //创建动态代理
        DynamicPurchasing dynamicPurchasing = new DynamicPurchasing(man);
        ClassLoader loader = man.getClass().getClassLoader();
        //动态创建代理类
        IShop purchase = (IShop) Proxy.newProxyInstance(loader,new Class[]{IShop.class},dynamicPurchasing);
        purchase.buy();
    }
}
