package com.example.administrator.practice.basis.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Car car = new Car();
        //通过具体对象获取 Class对象
        Class clazz = car.getClass();
        //通过类名获取 Class对象
        Class clazz1 = Car.class;
        //通过全限类名获取 Class对象
        try {
            Class clazz2 = Class.forName("com.example.administrator.practice.basis.reflect.Car");
            //通过类加载器获取 Class对象
            Class clazz3 = Test.class.getClassLoader().loadClass("com.example.administrator.practice.basis.reflect.Car");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //通过类对象得到 全限类名
        System.out.println(clazz.getName());
        //通过类对象得到 类名
        System.out.println(clazz.getSimpleName());
        //通过类对象得到 包名
        System.out.println(clazz.getPackage());

        try {
            //获取公共属性
            Field field = clazz.getField("b");
            //获取属性名
            System.out.println(field.getName());
            //获取修饰符
            System.out.println(field.getModifiers());
            //获取数据类型
            System.out.println(field.getType());
            field.set(car, 2);
            System.out.println("b属性赋值："+field.get(car));
            //获取所有所有属性
            Field[] fields = clazz.getFields();
            Field[] fields1 = clazz.getDeclaredFields(); //包括私有属性
            //获取私有属性
            Field field2 = clazz.getDeclaredField("a");
            //设置私有权限能够访问
            field2.setAccessible(true);
            field2.set(car,3);
            System.out.println("a属性赋值："+field2.get(car));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //获取包括自身和继承（实现）过来的所有的public方法
        Method[] methods = clazz.getMethods();
        //获取自身所有的方法(private、public、protected，和访问权限无关)，不包括继承的
        Method[] methods1 = clazz.getDeclaredMethods();

        try {
            //获取某个public方法,，包括继承的
            Method method = clazz.getMethod("run",null);
            method.invoke(car,null);
            Method method1 = clazz.getMethod("run",String.class);
            method1.invoke(car,"555");
            Method method2 = clazz.getDeclaredMethod("go",null);
            method2.setAccessible(true);
            method2.invoke(car,null);
            //获取方法返回值
            System.out.println("方法返回值："+method2.getReturnType());
            //获取方法参数列表
            System.out.println("方法参数列表："+ Arrays.toString(method1.getParameterTypes()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
