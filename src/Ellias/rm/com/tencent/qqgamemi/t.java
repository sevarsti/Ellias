package com.tencent.qqgamemi;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;

class t
  implements Animator.AnimatorListener
{
  public int a;

  t(QMiSpirit paramQMiSpirit)
  {
  }

  public void onAnimationCancel(Animator paramAnimator)
  {
    this.b.t = false;
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    this.b.t = true;
    QMiSpirit.a(this.b, this.a);
    this.b.a(this.a);
  }

  public void onAnimationRepeat(Animator paramAnimator)
  {
  }

  public void onAnimationStart(Animator paramAnimator)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.t
 * JD-Core Version:    0.6.0
 */