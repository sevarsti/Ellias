package com.tencent.qqgamemi.animation;

import com.tencent.component.utils.log.LogUtil;

class c
  implements FrameDrawable.FrameAnimationListener
{
  c(FrameBackGroundAction paramFrameBackGroundAction)
  {
  }

  public void a(FrameDrawable paramFrameDrawable)
  {
    LogUtil.d("Animation", "start bg");
  }

  public void a(FrameDrawable paramFrameDrawable, int paramInt)
  {
    LogUtil.d("Animation", "progress bg" + paramInt);
  }

  public void b(FrameDrawable paramFrameDrawable)
  {
    if (!this.a.c())
      this.a.e();
    LogUtil.d("Animation", "end bg");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.c
 * JD-Core Version:    0.6.0
 */