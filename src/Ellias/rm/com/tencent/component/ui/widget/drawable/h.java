package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class h extends Drawable.ConstantState
{
  Bitmap a;
  int b;
  int c = 160;
  Paint d;

  h(Bitmap paramBitmap)
  {
    this.a = paramBitmap;
    this.d = new Paint(6);
  }

  h(h paramh)
  {
    this(paramh.a);
    this.b = paramh.b;
    this.c = paramh.c;
    this.d = new Paint(paramh.d);
  }

  public int getChangingConfigurations()
  {
    return this.b;
  }

  public Drawable newDrawable()
  {
    return new LightweightBitmapDrawable(this, null, null);
  }

  public Drawable newDrawable(Resources paramResources)
  {
    return new LightweightBitmapDrawable(this, paramResources, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.h
 * JD-Core Version:    0.6.0
 */