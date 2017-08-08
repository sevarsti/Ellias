package com.tencent.qqgamemi.animation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.tencent.component.ComponentContext;

public class FrameAction extends QmiSpiritAction
{
  private static final int b = 25;
  private FrameDrawable a;
  private ImageView c = null;
  private Drawable[] d;
  private FrameDrawable.FrameAnimationListener e = new a(this);

  public FrameAction(int[] paramArrayOfInt)
  {
    Drawable[] arrayOfDrawable = new Drawable[paramArrayOfInt.length];
    for (int i = 0; i < paramArrayOfInt.length; i++)
      arrayOfDrawable[i] = ComponentContext.a().getResources().getDrawable(paramArrayOfInt[i]);
    a(arrayOfDrawable);
  }

  public FrameAction(Drawable[] paramArrayOfDrawable)
  {
    a(paramArrayOfDrawable);
  }

  private void a(Drawable[] paramArrayOfDrawable)
  {
    this.a = new FrameDrawable();
    for (int i = 0; i < paramArrayOfDrawable.length; i++)
      this.a.addFrame(paramArrayOfDrawable[i], 25);
    this.a.a(this.e);
    this.d = paramArrayOfDrawable;
  }

  protected void a()
  {
    if (this.a != null)
      this.a.stop();
  }

  public void a(ImageView paramImageView)
  {
    paramImageView.setImageDrawable(this.a);
    this.c = paramImageView;
  }

  public void a(AnimationParam paramAnimationParam)
  {
    this.a.start();
  }

  public void b()
  {
    a(false);
    this.a.unscheduleSelf(null);
    if (this.c != null)
      this.c.setImageDrawable(null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.animation.FrameAction
 * JD-Core Version:    0.6.0
 */