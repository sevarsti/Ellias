package com.tencent.qqgamemi;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.tencent.qqgamemi.common.TLog;

final class ae
  implements Animator.AnimatorListener
{
  public void onAnimationCancel(Animator paramAnimator)
  {
    TLog.c(QMiWindowManager.m(), "hideInflateViewPushOut onAnimationEnd isInflateViewAdded=" + QMiWindowManager.s());
    QMiWindowManager.r();
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    TLog.c(QMiWindowManager.m(), "hideInflateViewPushOut onAnimationEnd isInflateViewAdded=" + QMiWindowManager.s());
    QMiWindowManager.r();
  }

  public void onAnimationRepeat(Animator paramAnimator)
  {
  }

  public void onAnimationStart(Animator paramAnimator)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ae
 * JD-Core Version:    0.6.0
 */