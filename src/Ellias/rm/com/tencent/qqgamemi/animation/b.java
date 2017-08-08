package com.tencent.qqgamemi.animation;

import android.widget.ImageView;
import com.tencent.component.utils.log.LogUtil;

class b
  implements FrameDrawable.FrameAnimationListener
{
  b(FrameBackGroundAction paramFrameBackGroundAction)
  {
  }

  public void a(FrameDrawable paramFrameDrawable)
  {
    this.a.d();
    LogUtil.d("Animation", "start");
  }

  public void a(FrameDrawable paramFrameDrawable, int paramInt)
  {
    AnimationParam localAnimationParam = new AnimationParam();
    localAnimationParam.g = paramInt;
    this.a.c(localAnimationParam);
    if ((!this.a.c()) && (paramInt == FrameBackGroundAction.d(this.a)))
      FrameBackGroundAction.e(this.a).start();
    LogUtil.d("Animation", "progress" + paramInt);
  }

  public void b(FrameDrawable paramFrameDrawable)
  {
    FrameBackGroundAction.b(this.a).setBackgroundDrawable(FrameBackGroundAction.a(this.a)[(-1 + FrameBackGroundAction.a(this.a).length)]);
    FrameBackGroundAction.b(this.a).setImageDrawable(FrameBackGroundAction.c(this.a)[(-1 + FrameBackGroundAction.c(this.a).length)]);
    LogUtil.d("Animation", "end");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.b
 * JD-Core Version:    0.6.0
 */