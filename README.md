
参考：

[handler的使用—如何实现Android计时与倒计时的几种方法](https://www.xuebuyuan.com/51269.html)

[]()

# 验证码倒计时

- handler.sendEmptyMessageDelayed

> mHandler.sendEmptyMessage(MSG_CODE);


> mHandler.sendEmptyMessageDelayed(MSG_CODE, 1000);

- Handler与Runnable（最简洁）
 
> mHandler.post(mRunnable);
 
> mHandler.postDelayed(mRunnable, 1000);

- CountDownTimer

> mCountDownTimer.start();

- TimerTask与Timer

>  mTimer.schedule(mTimerTask, 0, 1000); 













