package com.example.administrator.practice.threadlearn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority){
        this.priority = priority;
    }

    public String toString(){
        return Thread.currentThread()+":"+ countDown;
    }

    @Override
    public void run() {
        while (true){
            for(int i = 1;i < 10000;i++){
                d += (Math.PI+Math.E) / (double)i;
                if(i % 1000 == 0){
                    Thread.yield();
                }
            }
            System.out.print(this);
            if(--countDown == 0){
                return;
            }
        }
    }

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0;i < 5;i++){
            service.execute(new SimplePriorities(Thread.MIN_PRIORITY));
            service.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        }
        service.shutdown();
    }
}
