package com.ts.demo;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class SingletonDemo {
    private static volatile SingletonDemo mSingleton;

    private SingletonDemo(){

    }

    public static SingletonDemo getInstance() {
        if (mSingleton == null) {
            synchronized (SingletonDemo.class) {
                if (mSingleton == null) {
                    mSingleton = new SingletonDemo();
                }
            }
        }
        return mSingleton;
    }

}
