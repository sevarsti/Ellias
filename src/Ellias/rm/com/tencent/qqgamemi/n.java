package com.tencent.qqgamemi;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.tencent.qqgamemi.animation.AnimationManager;

class n
  implements Animator.AnimatorListener
{
  n(QMiSpirit paramQMiSpirit, int paramInt)
  {
  }

  public void onAnimationCancel(Animator paramAnimator)
  {
    AnimationManager.i.a(this.a, QMiSpirit.c(this.b), null);
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    this.b.B = true;
    AnimationManager.i.a(this.a, QMiSpirit.c(this.b), null);
    QMiSpirit.a(this.b, QMiServiceLogic.b);
  }

  public void onAnimationRepeat(Animator paramAnimator)
  {
  }

  public void onAnimationStart(Animator paramAnimator)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.n
 * JD-Core Version:    0.6.0
 */