package com.liuhe.demoipc;

/**
 * 双重锁懒汉模式(Double Check Lock)
 * DCL模式的优点就是，只有在对象需要被使用时才创建，第一次判断 INSTANCE == null为了避免非必要加锁，
 * 当第一次加载时才对实例进行加锁再实例化。这样既可以节约内存空间，又可以保证线程安全。
 * 但是，由于jvm存在乱序执行功能，DCL也会出现线程不安全的情况。
 * instance = new Singleton5();在JVM中的执行步骤
 * 1.在堆内存开辟内存空间。
 * 2.在堆内存中实例化SingleTon里面的各个参数。
 * 3.把对象指向堆内存空间。
 * <p>
 * 由于jvm存在乱序执行功能，所以可能在2还没执行时就先执行了3，如果此时再被切换到线程B上，由于执行了3，instance 已经非空了，会被直接拿出来用，这样的话，就会出现异常。这个就是著名的DCL失效问题。
 */
public class Singleton5 {

    private static Singleton5 instance;

    public Singleton5() {
    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }

}
