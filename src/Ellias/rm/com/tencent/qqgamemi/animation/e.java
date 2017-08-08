package com.tencent.qqgamemi.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;

class e extends Handler
{
  e(ShakeAction paramShakeAction, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    if (this.a.c())
      return;
    if (ShakeAction.a(this.a) == 0)
      this.a.d();
    AnimationParam localAnimationParam = (AnimationParam)ShakeAction.b(this.a).get(ShakeAction.a(this.a));
    ShakeAction.a(this.a, localAnimationParam.a, localAnimationParam.b);
    ShakeAction.c(this.a);
    if (ShakeAction.a(this.a) == 8)
    {
      this.a.e();
      return;
    }
    sendEmptyMessageDelayed(0, 40L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.e
 * JD-Core Version:    0.6.0
 */