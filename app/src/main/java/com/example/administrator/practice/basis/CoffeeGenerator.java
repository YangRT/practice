package com.example.administrator.practice.basis;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CoffeeGenerator implements Generator<Coffee>,Iterable<Coffee> {
    private int size;
    public CoffeeGenerator(){}
    public CoffeeGenerator(int sz){size = sz;}

    @Override
    public Coffee next() {
        try {
            return Coffee.class.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    class CoffeeIterator implements Iterator<Coffee>{
        int count = size;

        public boolean hasNext(){return count>0;}

        public Coffee next(){
            count--;
            return CoffeeGenerator.this.next();
        }
        public void remove(){

        }
    };

    @NonNull
    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }
}
