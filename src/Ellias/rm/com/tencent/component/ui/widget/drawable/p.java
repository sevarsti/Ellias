package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class p extends a
{
  float c;
  float[] d;
  boolean e;
  boolean f;

  p(Drawable paramDrawable, DrawableContainer paramDrawableContainer)
  {
    super(paramDrawable, paramDrawableContainer);
    this.c = 0.0F;
    this.d = null;
    this.e = false;
    this.f = false;
  }

  p(p paramp, DrawableContainer paramDrawableContainer, Resources paramResources)
  {
    super(paramp, paramDrawableContainer, paramResources);
    this.c = paramp.c;
    this.d = a(paramp.d);
    this.e = paramp.e;
    this.f = paramp.f;
  }

  private static float[] a(float[] paramArrayOfFloat)
  {
    float[] arrayOfFloat;
    if (paramArrayOfFloat == null)
      arrayOfFloat = null;
    int i;
    do
    {
      return arrayOfFloat;
      i = paramArrayOfFloat.length;
      arrayOfFloat = new float[i];
    }
    while (i <= 0);
    System.arraycopy(paramArrayOfFloat, 0, arrayOfFloat, 0, i);
    return arrayOfFloat;
  }

  public Drawable newDrawable()
  {
    return new RoundCornerDrawable(this, null, null);
  }

  public Drawable newDrawable(Resources paramResources)
  {
    return new RoundCornerDrawable(this, paramResources, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.p
 * JD-Core Version:    0.6.0
 */