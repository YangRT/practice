package com.example.administrator.practice.basis.reflect;

public class Car {

    private int a;

    public int b;

    public void run(){
        System.out.println("public method invoke");
    }

    public void run(String what){
        System.out.println("public method with parameter invoke");
    }

    private void go(){
        System.out.println("private method invoke");
    }
}
