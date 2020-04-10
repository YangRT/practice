package com.example.administrator.practice.charter4;

import android.icu.util.TimeUnit;

public class StopThread {
//    public static void main(String[] args) throws InterruptedException{
//        MoonRunner runner = new MoonRunner();
//        Thread thread = new Thread(runner);
//        thread.start();
//        java.util.concurrent.TimeUnit.MILLISECONDS.sleep(10);
//        thread.interrupt();
//    }

    public static class MoonRunner implements Runnable{
        private long i;
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                i++;
                System.out.println("i="+ i);
            }
            System.out.println("Stop!");
        }
    }

}
