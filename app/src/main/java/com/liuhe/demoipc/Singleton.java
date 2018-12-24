package com.liuhe.demoipc;

/**
 * Singleton helper class for lazily initialization.
 * 用于延迟初始化的Singleton帮助器类。
 * @param <T>
 */
public abstract class Singleton<T> {
    /**
     * 一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
     * 1.保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
     * 2.禁止进行指令重排序。
     */
    private volatile T mInstance;

    protected abstract T create();

    public final T get() {
        if (mInstance != null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = create();
                }
                return mInstance;
            }
        }
        return mInstance;
    }
}
