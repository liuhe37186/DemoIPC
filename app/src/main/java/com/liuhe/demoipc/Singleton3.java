package com.liuhe.demoipc;

/**
 * 饿汉
 *
 线程不安全问题主要是由于 uniqueInstance 被实例化多次，采取直接实例化 uniqueInstance 的方式就不会产生线程不安全问题。

 但是直接实例化的方式也丢失了延迟实例化带来的节约资源的好处。
 */
public class Singleton3 {
    private static final Singleton3 instance = new Singleton3();

    private Singleton3(){

    }

    public static Singleton3 getInstance(){
        return instance;
    }
}
