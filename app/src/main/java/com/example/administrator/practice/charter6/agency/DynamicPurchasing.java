package com.example.administrator.practice.charter6.agency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//动态代理类,java提供动态代理接口InvocationHandler
public class DynamicPurchasing implements InvocationHandler {
    private Object obj;

    public DynamicPurchasing(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj,args);
        if(method.getName().equals("buy")){
            System.out.println("买买买");
        }
        return result;
    }
}
