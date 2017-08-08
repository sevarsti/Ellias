package com.tencent.qqgamemi;

import android.view.View;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;

class af
  implements Animator.AnimatorListener
{
  View a;

  public af(View paramView)
  {
    this.a = paramView;
  }

  public void onAnimationCancel(Animator paramAnimator)
  {
    this.a.setVisibility(8);
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    this.a.setVisibility(8);
  }

  public void onAnimationRepeat(Animator paramAnimator)
  {
  }

  public void onAnimationStart(Animator paramAnimator)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.af
 * JD-Core Version:    0.6.0
 */