package com.ts.demo.observer;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class Wheater {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wheater(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Wheater{" +
                "description='" + description + '\'' +
                '}';
    }
}
