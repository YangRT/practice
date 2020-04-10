package com.example.administrator.practice.threadlearn;

public class MainThread {

    public static void main(String[] args){
//        LifeOff lifeOff = new LifeOff();
//        lifeOff.run();
        Thread thread = new Thread(new LifeOff());
        thread.start();
    }
}
