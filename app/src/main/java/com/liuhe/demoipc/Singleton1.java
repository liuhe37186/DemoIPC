package com.liuhe.demoipc;

/**
 * 懒汉
 * 以下实现中，私有静态变量 instance 被延迟实例化，这样做的好处是，如果没有用到该类，那么就不会实例化 instance，从而节约资源。

 这个实现在多线程环境下是不安全的，如果多个线程能够同时进入 if (instance == null) ，并且此时 instance 为 null，那么会有多个线程执行 instance = new Singleton(); 语句，这将导致实例化多次 instance。
 *
 */
public class Singleton1 {


    private static Singleton1 instance;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if(instance == null){
            instance = new Singleton1();
        }

        return instance;
    }
}
