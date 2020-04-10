package com.example.administrator.practice.threadlearn;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        ArrayList<Future<String>> list = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            list.add(service.submit(new TaskWithResult(i)));
        }
        for(Future<String> str:list){
            try {
                System.out.print(str.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                service.shutdown();
            }
        }
    }
}
