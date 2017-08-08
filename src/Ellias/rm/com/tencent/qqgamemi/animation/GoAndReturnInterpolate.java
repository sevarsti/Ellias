package com.tencent.qqgamemi.animation;

import android.view.animation.Interpolator;

public class GoAndReturnInterpolate
  implements Interpolator
{
  Interpolator a;
  Interpolator b;

  public GoAndReturnInterpolate(Interpolator paramInterpolator)
  {
    this.a = paramInterpolator;
  }

  public GoAndReturnInterpolate(Interpolator paramInterpolator1, Interpolator paramInterpolator2)
  {
    this.a = paramInterpolator1;
    this.b = paramInterpolator2;
  }

  public float getInterpolation(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat < 0.5D))
      return this.a.getInterpolation(paramFloat * 2.0F);
    if (this.b == null)
      return this.a.getInterpolation(2.0F * (1.0F - paramFloat));
    return this.b.getInterpolation(2.0F * (1.0F - paramFloat));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.GoAndReturnInterpolate
 * JD-Core Version:    0.6.0
 */