package com.tencent.qqgamemi.animation;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.tencent.qqgamemi.QMiSpirit;
import com.tencent.qqgamemi.QMiWindowManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ShakeAction extends QmiSpiritAction
{
  private static final int e = 8;
  private Handler a = new e(this, Looper.getMainLooper());
  private int b = 0;
  private ArrayList c = new ArrayList();
  private WeakReference d;

  public ShakeAction(QMiSpirit paramQMiSpirit)
  {
    this.d = new WeakReference(paramQMiSpirit);
  }

  private void a(int paramInt1, int paramInt2)
  {
    if (this.d != null)
      QMiWindowManager.b((QMiSpirit)this.d.get(), paramInt1, paramInt2);
  }

  private void d(AnimationParam paramAnimationParam)
  {
    int i = 0;
    AnimationParam localAnimationParam1 = paramAnimationParam;
    while (i < 8)
    {
      AnimationParam localAnimationParam2 = new AnimationParam();
      if (localAnimationParam1 == null)
      {
        localAnimationParam1 = new AnimationParam();
        localAnimationParam1.a = 0;
        localAnimationParam1.b = 0;
      }
      if ((i == 0) || (i == 4))
      {
        localAnimationParam2.a = localAnimationParam1.a;
        localAnimationParam2.b = (-5 + localAnimationParam1.b);
      }
      if ((i == 1) || (i == 3) || (i == 5) || (i == 7))
      {
        localAnimationParam2.a = localAnimationParam1.a;
        localAnimationParam2.b = localAnimationParam1.b;
      }
      if ((i == 2) || (i == 6))
      {
        localAnimationParam2.a = localAnimationParam1.a;
        localAnimationParam2.b = (5 + localAnimationParam1.b);
      }
      this.c.add(localAnimationParam2);
      i++;
    }
  }

  protected void a()
  {
    this.a.removeMessages(0);
  }

  public void a(ImageView paramImageView)
  {
  }

  public void a(AnimationParam paramAnimationParam)
  {
    d(paramAnimationParam);
    this.a.sendEmptyMessage(0);
  }

  public void b()
  {
    a(false);
    this.a.removeMessages(0);
    this.b = 0;
    this.c.clear();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.ShakeAction
 * JD-Core Version:    0.6.0
 */