package com.tencent.qqgamemi;

import android.widget.ImageView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.TLog;

class p
  implements Animator.AnimatorListener
{
  p(QMiSpirit paramQMiSpirit, ImageView paramImageView)
  {
  }

  public void onAnimationCancel(Animator paramAnimator)
  {
    this.a.setImageResource(ResourceUtil.c("qmi_turn_ball"));
    this.b.t = false;
    QMiSpirit.b(this.b, null);
  }

  public void onAnimationEnd(Animator paramAnimator)
  {
    TLog.c("Benson", "[QMiSprite] showHelloAnim onAnimationEnd");
    this.a.setImageResource(ResourceUtil.c("qmi_turn_ball"));
    QMiSpirit.a(this.b, this.b.F.a);
    this.b.a(this.b.F.a);
    QMiSpirit.b(this.b, null);
  }

  public void onAnimationRepeat(Animator paramAnimator)
  {
  }

  public void onAnimationStart(Animator paramAnimator)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.p
 * JD-Core Version:    0.6.0
 */