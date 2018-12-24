package com.liuhe.demoipc;


/**
 * 静态内部类
 * 当Singleton4类加载时，静态内部类SingletonHolder没有被加载进内存，只有当调用getInstance()方法时，
 * 才会触发SingletonHolder.INSTANCE，SingletonHolder才会被加载，此时初始化INSTANCE实例，并且JVM确保INSTANCE只被实例化一次
 * 这种方式不仅具有延迟初始化的好处，而且JVM提供了对线程安全的支持
 *
 */
public class Singleton4 {
    private Singleton4(){}

    private static class SingletonHolder{
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
