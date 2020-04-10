package com.example.administrator.practice.basis;

import java.util.ArrayList;
import java.util.Random;

//泛型类
public class RandomList<T> {

    private ArrayList<T> storage = new ArrayList<>();
    private Random random = new Random(47);

    public void add(T item){
        storage.add(item);
    }

    public T select(){
        return storage.get(random.nextInt(storage.size()));
    }
}
