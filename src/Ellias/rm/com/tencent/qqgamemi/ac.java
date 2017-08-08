package com.tencent.qqgamemi;

import android.view.WindowManager;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.tencent.qqgamemi.common.TLog;

final class ac
  implements Animator.AnimatorListener
{
  public void onAnimationCancel(Animator paramAnimator)
  {
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    if (QMiWindowManager.l() == true)
    {
      TLog.b(QMiWindowManager.m(), "hideQMiView isHalfSpiritShow:" + QMiWindowManager.n());
      if (QMiWindowManager.n())
        break label70;
    }
    while (true)
    {
      try
      {
        QMiWindowManager.p().removeView(QMiWindowManager.o());
        QMiWindowManager.a(false);
        return;
      }
      catch (Exception localException)
      {
        TLog.c(QMiWindowManager.m(), "wm hideFloatViewFadeOut", localException);
        continue;
      }
      label70: QMiWindowManager.q();
    }
  }

  public void onAnimationRepeat(Animator paramAnimator)
  {
  }

  public void onAnimationStart(Animator paramAnimator)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ac
 * JD-Core Version:    0.6.0
 */