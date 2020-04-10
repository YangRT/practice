package com.example.administrator.practice.charter9;

import java.lang.reflect.Method;
//运行时注解处理器
public class AnnotationProcessor {
    public static void main(String[] args){
        Method[] methods = AnnotationTest.class.getDeclaredMethods();
        for(Method m:methods){
            GET get = m.getAnnotation(GET.class);
            System.out.println(get.value());
        }
    }
}
