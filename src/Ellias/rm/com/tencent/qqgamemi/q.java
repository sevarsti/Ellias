package com.tencent.qqgamemi;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.tencent.qqgamemi.animation.AnimationManager;

class q
  implements Animator.AnimatorListener
{
  q(QMiSpirit paramQMiSpirit, boolean paramBoolean)
  {
  }

  public void onAnimationCancel(Animator paramAnimator)
  {
    QMiSpirit.b(this.b).f = false;
    if (this.a)
      AnimationManager.i.a(QMiSpirit.c(this.b), this.b.C);
    while (true)
    {
      QMiSpirit.a(this.b, false);
      return;
      AnimationManager.i.a(QMiSpirit.c(this.b), null);
    }
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    QMiSpirit.b(this.b).f = false;
    if (this.a)
      AnimationManager.i.a(QMiSpirit.c(this.b), this.b.C);
    while (true)
    {
      QMiSpirit.a(this.b, false);
      this.b.B = true;
      return;
      AnimationManager.i.a(QMiSpirit.c(this.b), null);
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
 * Qualified Name:     com.tencent.qqgamemi.q
 * JD-Core Version:    0.6.0
 */