package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class f extends Drawable.ConstantState
{
  Bitmap a;
  int b;
  int c;
  int d;
  int e = 160;
  Paint f;

  f(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    this.a = paramBitmap;
    this.b = paramInt1;
    this.c = paramInt2;
    this.f = new Paint(6);
  }

  f(f paramf)
  {
    this(paramf.a, paramf.b, paramf.c);
    this.d = paramf.d;
    this.e = paramf.e;
    this.f = new Paint(paramf.f);
  }

  public int getChangingConfigurations()
  {
    return this.d;
  }

  public Drawable newDrawable()
  {
    return new ImageDrawable(this, null, null);
  }

  public Drawable newDrawable(Resources paramResources)
  {
    return new ImageDrawable(this, paramResources, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.f
 * JD-Core Version:    0.6.0
 */