package com.example.administrator.practice.charter4;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {
    public static class MyTestCallble implements Callable{
        @Override
        public String call() throws Exception {
            return "Hello world";
        }
    }

//    public static void main(String[] args){
//        MyTestCallble callble = new MyTestCallble();
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        Future future = service.submit(callble);
//        try{
//            System.out.println(future.get());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
