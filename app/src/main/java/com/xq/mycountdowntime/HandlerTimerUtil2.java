package com.xq.mycountdowntime;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class HandlerTimerUtil2 {
    private int count;
    private OnCountDownListener mCountDownListener;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            count--;
            if (count < 0) {
                mCountDownListener.onFinish();
                count = 10;
            } else {
                mCountDownListener.onTick(count);
                mHandler.postDelayed(mRunnable, 1000);
            }
        }
    };

    public HandlerTimerUtil2(int count) {
        this.count = count;
    }

    public void start() {
        mHandler.post(mRunnable);
    }

    public void cancel() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }


    public interface OnCountDownListener {
        void onFinish();

        void onTick(int count);
    }

    public void setCountDownListener(OnCountDownListener countDownListener) {
        mCountDownListener = countDownListener;
    }
}
