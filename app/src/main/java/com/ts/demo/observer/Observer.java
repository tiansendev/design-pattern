package com.ts.demo.observer;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public interface Observer<T> {
    void update(Observable<T> observable,T data);
}
