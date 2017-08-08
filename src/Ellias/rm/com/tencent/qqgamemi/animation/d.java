package com.tencent.qqgamemi.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class d extends Handler
{
  d(FrameDrawable paramFrameDrawable, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
    case 3:
    case 2:
    }
    FrameDrawable.FrameAnimationListener localFrameAnimationListener1;
    do
    {
      do
      {
        FrameDrawable.FrameAnimationListener localFrameAnimationListener2;
        do
        {
          do
            return;
          while (FrameDrawable.a(this.a) == null);
          localFrameAnimationListener2 = (FrameDrawable.FrameAnimationListener)FrameDrawable.a(this.a).get();
        }
        while (localFrameAnimationListener2 == null);
        localFrameAnimationListener2.b(this.a);
        return;
      }
      while (FrameDrawable.a(this.a) == null);
      localFrameAnimationListener1 = (FrameDrawable.FrameAnimationListener)FrameDrawable.a(this.a).get();
    }
    while (localFrameAnimationListener1 == null);
    int i = paramMessage.arg1;
    localFrameAnimationListener1.a(this.a, i);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.d
 * JD-Core Version:    0.6.0
 */