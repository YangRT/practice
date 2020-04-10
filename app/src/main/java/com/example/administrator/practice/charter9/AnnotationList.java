package com.example.administrator.practice.charter9;

public class AnnotationList {
    // 1.标准注解
    // @Override  对覆盖超类的方法进行标记
    // @Deprecated 对不鼓励使用或已过时方法添加注解
    // @SuppressWarnings 选择性地取消特定代码段的警告
    // @SafeVarargs 用来声明可变长参数的方法

    //2.元注解 （用来注解其他注解，从而创建新的注解）
    //@Targe  注解所修饰的范围
    //  取值： ElementType.TYPE :能修饰类，接口，或枚举类型
    //         ElementType.FIELD: 能修饰成员变量
    //         ElementType.METHOD: 能修饰方法
    //         ElementType.PARAMETER: 能修饰参数
    //         ElementType.CONSTRUCTOR: 能修饰构造方法
    //         ElementType.LOCAL_VARIABLE: 能修饰局部变量
    //         ElementType.ANNOTATION_TYPE: 能修饰注解
    //         ElementType.PACKAGE: 能修饰包
    //         ElementType.TYPE_PARAMETER: 类型参数说明
    //         ElementType.USE： 使用类型
    //@Inherited  表示注解可以被继承
    //@Documented  表示这个注解应该被JavaDoc工具记录
    //@Retention 用来声明注解的保留策略
    // 取值： RetentionPolicy.SOURCE: 源码级注解 注解信息只会保留在 .java源码中
    //                                源码编译后，注解信息丢失，不会保留在.class文件中
    //        RetentionPolicy.CLASS：编译时注解，注解信息保留在.java文件和.class文件中
    //                               当运行java程序时，JVM会丢弃注解信息，不会保留在JVM
    //       RetentionPolicy.RUNTIME：运行时注解。当运行java程序时，JVM会保留注解信息
    //@Repeatable 允许一个注解在同一声明类型（类，属性或方法）上多次使用
}
