package com.tencent.component.ui.widget.image;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class i
  implements Animation.AnimationListener
{
  i(ExtendImageView paramExtendImageView, Runnable paramRunnable)
  {
  }

  public void onAnimationEnd(Animation paramAnimation)
  {
    if (this.a != null)
      this.a.run();
  }

  public void onAnimationRepeat(Animation paramAnimation)
  {
  }

  public void onAnimationStart(Animation paramAnimation)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.i
 * JD-Core Version:    0.6.0
 */