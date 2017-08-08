package com.tencent.qqgamemi.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.component.utils.log.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FrameDrawable extends AnimationDrawable
{
  private static final int c = 2;
  private static final int d = 3;
  ArrayList a = new ArrayList();
  private WeakReference b;
  private Handler e = new d(this, Looper.getMainLooper());

  public FrameDrawable()
  {
    setOneShot(true);
  }

  public void a(Drawable paramDrawable, int paramInt, boolean paramBoolean)
  {
    addFrame(paramDrawable, paramInt);
    this.a.add(Boolean.valueOf(paramBoolean));
  }

  public void a(FrameDrawable.FrameAnimationListener paramFrameAnimationListener)
  {
    this.b = new WeakReference(paramFrameAnimationListener);
  }

  public boolean selectDrawable(int paramInt)
  {
    boolean bool = super.selectDrawable(paramInt);
    LogUtil.d("Animation", "index:" + paramInt);
    if (this.a.size() > paramInt)
    {
      Message localMessage = Message.obtain();
      localMessage.what = 2;
      localMessage.arg1 = paramInt;
      this.e.sendMessage(localMessage);
    }
    if ((paramInt == -1 + getNumberOfFrames()) && (paramInt != 0))
      this.e.sendEmptyMessage(3);
    return bool;
  }

  public void start()
  {
    if ((!isRunning()) && (this.b != null))
    {
      FrameDrawable.FrameAnimationListener localFrameAnimationListener = (FrameDrawable.FrameAnimationListener)this.b.get();
      if (localFrameAnimationListener != null)
        localFrameAnimationListener.a(null);
    }
    super.start();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.FrameDrawable
 * JD-Core Version:    0.6.0
 */