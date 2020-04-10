package com.example.administrator.practice.charter6.flyweight;
//客户端调用
public class Client {
    public static void main(String[] args){
        Goods goods = GoodsFactory.getGoods("iphone7");
        goods.showGoodsPrice("32G");
        Goods goods2 = GoodsFactory.getGoods("iphone7");
        goods2.showGoodsPrice("128G");
    }
}
