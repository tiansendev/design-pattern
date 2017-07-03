package com.ts.demo.rx;

import android.util.Log;

import com.ts.demo.observer.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class RxDemo {
    static List<Map<Integer, String>> mList = new ArrayList<>();
    public static void testRx() {


        Map<Integer, List<String>> mMap = initMap();
        Log.w("initMap", "mMap---->" + mMap);


        io.reactivex.Observable
                .fromIterable(mMap.entrySet())
                .flatMap(new Function<Map.Entry<Integer, List<String>>, ObservableSource<Map<Integer,String>>>() {
                    @Override
                    public ObservableSource<Map<Integer,String>> apply(@NonNull Map.Entry<Integer, List<String>> integerListEntry) throws Exception {
                        final int key = integerListEntry.getKey();
                        return io.reactivex.Observable
                                .fromIterable(integerListEntry.getValue())
                                .filter(new Predicate<String>() {
                                    @Override
                                    public boolean test(@NonNull String s) throws Exception {
                                        if (s.equals("list1"))
                                            return true;
                                        return false;
                                    }
                                })
                                .flatMap(new Function<String, ObservableSource<Map<Integer, String>>>() {
                                    @Override
                                    public ObservableSource<Map<Integer, String>> apply(@NonNull String s) throws Exception {
                                        Map<Integer, String> map = new HashMap<Integer, String>();
                                        map.put(key, s);
                                        return io.reactivex.Observable.just(map);
                                    }
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<Integer, String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Map<Integer, String> integerStringMap) {
                        Log.e("result", "result map--->"+integerStringMap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private static Map<Integer,List<String>> initMap() {
        Map<Integer, List<String>> map = new LinkedHashMap<>();
        for (int i = 0; i<10; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                list.add("list"+j);
            }
            map.put(i, list);
        }
        return map;
    }
}
