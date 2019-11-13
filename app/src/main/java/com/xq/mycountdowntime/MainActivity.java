package com.xq.mycountdowntime;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private HandlerTimerUtil mHandlerTimerUtil;
    private HandlerTimerUtil2 mHandlerTimerUtil2;
    private Timer mTimer = new Timer();
    private int recLen = 10;
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    recLen--;
                    if (recLen < 0) {
                        recLen = 10;
                        mTv4.setAlpha(1.0f);
                        mTv4.setEnabled(true);
                        mTv4.setText("获取验证码");
                    } else {
                        mTv4.setAlpha(0.5f);
                        mTv4.setEnabled(false);
                        mTv4.setText(String.format("%d秒后重试", recLen));
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);
        mTv3 = findViewById(R.id.tv3);
        mTv4 = findViewById(R.id.tv4);
        mTv5 = findViewById(R.id.tv5);
        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);
        mTv4.setOnClickListener(this);
        mTv5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                mCountDownTimer.start();
                break;
            case R.id.tv2:
                if (mHandlerTimerUtil == null) {
                    mHandlerTimerUtil = new HandlerTimerUtil(10);
                    mHandlerTimerUtil.setCountDownListener(new HandlerTimerUtil.OnCountDownListener() {
                        @Override
                        public void onFinish() {
                            mTv2.setAlpha(1.0f);
                            mTv2.setEnabled(true);
                            mTv2.setText("获取验证码");
                        }

                        @Override
                        public void onTick(int count) {
                            mTv2.setAlpha(0.5f);
                            mTv2.setEnabled(false);
                            mTv2.setText(String.format("%d秒后重试", count));
                        }
                    });
                }
                mHandlerTimerUtil.start();
                break;
            case R.id.tv3:
                if (mHandlerTimerUtil2 == null) {
                    mHandlerTimerUtil2 = new HandlerTimerUtil2(10);
                    mHandlerTimerUtil2.setCountDownListener(new HandlerTimerUtil2.OnCountDownListener() {
                        @Override
                        public void onFinish() {
                            mTv3.setAlpha(1.0f);
                            mTv3.setEnabled(true);
                            mTv3.setText("获取验证码");
                        }

                        @Override
                        public void onTick(int count) {
                            mTv3.setAlpha(0.5f);
                            mTv3.setEnabled(false);
                            mTv3.setText(String.format("%d秒后重试", count));
                        }
                    });
                }
                mHandlerTimerUtil2.start();
                break;

            case R.id.tv4:
                mTimer.schedule(mTimerTask, 0, 1000);       // timeTask
                break;

            case R.id.tv5:
                break;
        }
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(10 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTv1.setAlpha(0.5f);
            mTv1.setEnabled(false);
            mTv1.setText(String.format("%d秒后重试", millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            mTv1.setAlpha(1.0f);
            mTv1.setEnabled(true);
            mTv1.setText("获取验证码");
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }

        if (mHandlerTimerUtil != null) {
            mHandlerTimerUtil.cancel();
        }

        if (mHandlerTimerUtil2 != null) {
            mHandlerTimerUtil2.cancel();
        }

        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }

        if (mHandlerTimerUtil != null) {
            mHandlerTimerUtil.cancel();
        }

        if (mHandlerTimerUtil2 != null) {
            mHandlerTimerUtil2.cancel();
        }

        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
