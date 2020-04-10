package com.example.administrator.practice.basis;

public class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    public String toString(){
        return getClass().getSimpleName()+" "+id;
    }
    //泛型方法 需将泛型参数列表置于返回值前
    private <T> void print(T t){
        System.out.println(t.getClass().getSimpleName());
    }

}
