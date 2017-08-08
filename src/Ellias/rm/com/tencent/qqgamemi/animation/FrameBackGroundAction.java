package com.tencent.qqgamemi.animation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.tencent.component.ComponentContext;

public class FrameBackGroundAction extends QmiSpiritAction
{
  public static final int a = 25;
  private FrameDrawable b;
  private FrameDrawable c;
  private ImageView d = null;
  private Drawable[] e;
  private Drawable[] f;
  private int g = 0;
  private FrameDrawable.FrameAnimationListener h = new b(this);
  private FrameDrawable.FrameAnimationListener i = new c(this);

  public FrameBackGroundAction(int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt)
  {
    Drawable[] arrayOfDrawable1 = new Drawable[paramArrayOfInt1.length];
    for (int k = 0; k < paramArrayOfInt1.length; k++)
      arrayOfDrawable1[k] = ComponentContext.a().getResources().getDrawable(paramArrayOfInt1[k]);
    a(arrayOfDrawable1, paramArrayOfInt2, paramArrayOfBoolean);
    Drawable[] arrayOfDrawable2 = new Drawable[paramArrayOfInt3.length];
    while (j < paramArrayOfInt3.length)
    {
      arrayOfDrawable2[j] = ComponentContext.a().getResources().getDrawable(paramArrayOfInt3[j]);
      j++;
    }
    a(arrayOfDrawable2, paramArrayOfInt4);
    this.g = paramInt;
  }

  private void a(Drawable[] paramArrayOfDrawable, int[] paramArrayOfInt)
  {
    this.c = new FrameDrawable();
    int j = 0;
    if (j < paramArrayOfDrawable.length)
    {
      if (paramArrayOfInt == null)
        this.c.addFrame(paramArrayOfDrawable[j], 25);
      while (true)
      {
        j++;
        break;
        this.c.addFrame(paramArrayOfDrawable[j], paramArrayOfInt[j]);
      }
    }
    this.c.a(this.i);
    this.f = paramArrayOfDrawable;
  }

  private void a(Drawable[] paramArrayOfDrawable, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean)
  {
    this.b = new FrameDrawable();
    int j = 0;
    if (j < paramArrayOfDrawable.length)
    {
      if (paramArrayOfInt == null)
        this.b.a(paramArrayOfDrawable[j], 25, paramArrayOfBoolean[j]);
      while (true)
      {
        j++;
        break;
        this.b.a(paramArrayOfDrawable[j], paramArrayOfInt[j], paramArrayOfBoolean[j]);
      }
    }
    this.b.a(this.h);
    this.e = paramArrayOfDrawable;
  }

  protected void a()
  {
    if (this.b != null)
      this.b.stop();
    if (this.c != null)
      this.c.stop();
  }

  public void a(ImageView paramImageView)
  {
    paramImageView.setImageDrawable(this.b);
    paramImageView.setBackgroundDrawable(this.c);
    this.d = paramImageView;
  }

  public void a(AnimationParam paramAnimationParam)
  {
    this.b.start();
  }

  public void a(boolean paramBoolean)
  {
    super.a(paramBoolean);
  }

  public void b()
  {
    a(false);
    this.b.unscheduleSelf(null);
    this.c.unscheduleSelf(null);
    if (this.d != null)
    {
      this.d.setImageDrawable(null);
      this.d.setBackgroundDrawable(null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.FrameBackGroundAction
 * JD-Core Version:    0.6.0
 */