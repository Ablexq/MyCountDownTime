package com.xq.mycountdowntime;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class HandlerTimerUtil {
    private int count;
    private OnCountDownListener mCountDownListener;
    private static final int MSG_CODE = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == MSG_CODE) {
                count--;
                if (count < 0) {
                    mCountDownListener.onFinish();
                    count = 10;
                } else {
                    mCountDownListener.onTick(count);
                    mHandler.sendEmptyMessageDelayed(MSG_CODE, 1000);
                }
            }
        }
    };

    public HandlerTimerUtil(int count) {
        this.count = count;
    }

    public void start() {
        mHandler.sendEmptyMessage(MSG_CODE);
    }

    public void cancel() {
        if (mHandler != null) {
            mHandler.removeMessages(MSG_CODE);
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
