package com.example.administrator.practice.threadlearn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//SingleThreadExecutor单线程执行，多任务需要排队等待处理
//CachedThreadPool()一般创建所需数量相同线程
//FixThreadPool() 限制线程数量 可复用线程
public class CachedThreadPool {
    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0;i < 5;i++){
            service.execute(new LifeOff());
        }
        service.shutdown();
    }
}
