package com.ts.demo.callbackDemo;

import android.icu.text.SimpleDateFormat;
import android.os.Handler;
import android.os.Message;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class CountDownTimer {

    private final String mCountDownTimeStr;
    private long mCurrentTime;

    private long mStartTime;

    private long mCountDownTime;

    private long mGap;

    private static final int INIT_TIME = 1;

    private onCountDownListener l;

    private boolean isFinish;

    public CountDownTimer(long mCountDownTime) {
        mCountDownTimeStr = calculate(mCountDownTime);
        this.mCountDownTime = mCountDownTime;
    }

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_TIME:
                    String dateStr = (String) msg.obj;
                    if (mCountDownTimeStr.endsWith(dateStr)) {
                        // end
                        isFinish = true;
                        l.onFinish();
                    } else {
                        l.onCounting(dateStr);
                    }

                    break;
            }
        }
    };

    public void start() {
        mStartTime = System.currentTimeMillis();
        new Thread() {
            @Override
            public void run() {
                while (!isFinish) {
                    try {
                        Thread.sleep(10);
                        mCurrentTime = System.currentTimeMillis();
                        mGap = mCurrentTime - mStartTime;
                        Message msg = Message.obtain(mHander);
                        msg.what = INIT_TIME;
                        msg.obj = calculate(mGap);
                        msg.sendToTarget();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private String calculate(long mGap) {
        SimpleDateFormat sf = new SimpleDateFormat("ss:SSS");
        return sf.format(new Date(mGap));
    }

    public void setOnCountDownListener (onCountDownListener l){
        if (l != null) this.l = l;
    }

    public interface onCountDownListener {
        void onCounting(String dateStr);
        void onFinish();
    }

}
