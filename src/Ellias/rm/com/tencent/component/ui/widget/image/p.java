package com.tencent.component.ui.widget.image;

import android.view.animation.Interpolator;

class p
  implements Runnable
{
  p(TransformImageView paramTransformImageView, long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
  }

  public void run()
  {
    float f1 = (float)(System.currentTimeMillis() - this.a) / this.b;
    if (f1 < 1.0F);
    for (int i = 1; ; i = 0)
    {
      float f2 = Math.max(Math.min(f1, 1.0F), 0.0F);
      float f3 = TransformImageView.a(this.g).getInterpolation(f2);
      float f4 = this.c + f3 * (this.d - this.c);
      this.g.c(f4, this.e, this.f);
      if (i != 0)
        this.g.post(this);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.p
 * JD-Core Version:    0.6.0
 */