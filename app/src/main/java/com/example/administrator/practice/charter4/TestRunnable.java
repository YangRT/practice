package com.example.administrator.practice.charter4;

public class TestRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello world");
    }

//    public static void main(String[] args){
//        TestRunnable runnable = new TestRunnable();
//        Thread thread = new Thread(runnable);
//        thread.start();
//    }
}
