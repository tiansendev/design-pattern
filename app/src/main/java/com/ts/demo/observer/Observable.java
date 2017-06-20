package com.ts.demo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class Observable<T> {
    private List<Observer<T>> observers = new ArrayList<>();

    public void register(Observer<T> observer) {
        if (observer == null) {
            throw new NullPointerException("Observer is null!");
        }
        synchronized (this) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }
    }

    public synchronized void unRegister(Observer<T> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(T data) {
        for (Observer<T> observer : observers) {
            observer.update(this,data);
        }
    }


}
