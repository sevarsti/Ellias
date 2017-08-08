package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.DisplayMetrics;

public class LightweightBitmapDrawable extends Drawable
{
  private static final int a = 6;
  private Bitmap b;
  private h c;
  private int d;
  private boolean e;
  private int f = -1;
  private int g = -1;
  private int h = -1;
  private int i = -1;

  public LightweightBitmapDrawable(Resources paramResources, Bitmap paramBitmap)
  {
    this(null, paramBitmap, -1, -1);
  }

  public LightweightBitmapDrawable(Resources paramResources, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    this.c = new h(paramBitmap);
    if (paramResources != null)
      this.d = paramResources.getDisplayMetrics().densityDpi;
    b(paramBitmap);
    a(paramInt1, paramInt2);
  }

  public LightweightBitmapDrawable(Bitmap paramBitmap)
  {
    this(null, paramBitmap, -1, -1);
  }

  public LightweightBitmapDrawable(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    this(null, paramBitmap, paramInt1, paramInt2);
  }

  private LightweightBitmapDrawable(h paramh, Resources paramResources)
  {
    this.c = new h(paramh);
    if (paramResources != null)
    {
      this.d = paramResources.getDisplayMetrics().densityDpi;
      if (paramh == null)
        break label81;
    }
    label81: for (Bitmap localBitmap = paramh.a; ; localBitmap = null)
    {
      b(localBitmap);
      return;
      if (paramh == null)
        break;
      this.d = paramh.c;
      break;
    }
  }

  private void c()
  {
    Bitmap localBitmap = this.b;
    if (localBitmap == null)
    {
      this.g = -1;
      this.f = -1;
      return;
    }
    int j = this.d;
    int k;
    if (j == 0)
    {
      k = localBitmap.getWidth();
      if (j != 0)
        break label65;
    }
    label65: for (int m = localBitmap.getHeight(); ; m = localBitmap.getScaledHeight(j))
    {
      this.f = k;
      this.g = m;
      return;
      k = localBitmap.getScaledWidth(j);
      break;
    }
  }

  public final Paint a()
  {
    return this.c.d;
  }

  public void a(int paramInt)
  {
    if (this.d != paramInt)
    {
      if (paramInt == 0)
        paramInt = 160;
      this.d = paramInt;
      if (this.b != null)
        c();
      invalidateSelf();
    }
  }

  public void a(int paramInt1, int paramInt2)
  {
    if ((this.h != paramInt1) || (this.i != paramInt2))
    {
      this.h = paramInt1;
      this.i = paramInt2;
    }
  }

  public void a(Bitmap paramBitmap)
  {
    b(paramBitmap);
  }

  protected final void a(Bitmap paramBitmap, boolean paramBoolean)
  {
    if (paramBitmap != this.b)
    {
      this.b = paramBitmap;
      c();
      if (paramBoolean)
        invalidateSelf();
    }
  }

  public void a(Canvas paramCanvas)
  {
    a(paramCanvas.getDensity());
  }

  public void a(DisplayMetrics paramDisplayMetrics)
  {
    a(paramDisplayMetrics.densityDpi);
  }

  public void a(boolean paramBoolean)
  {
    this.c.d.setAntiAlias(paramBoolean);
    invalidateSelf();
  }

  public Bitmap b()
  {
    return this.b;
  }

  protected final void b(Bitmap paramBitmap)
  {
    a(paramBitmap, true);
  }

  public void draw(Canvas paramCanvas)
  {
    Bitmap localBitmap = this.b;
    if (localBitmap != null)
    {
      h localh = this.c;
      paramCanvas.drawBitmap(localBitmap, null, getBounds(), localh.d);
    }
  }

  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.c.b;
  }

  public final Drawable.ConstantState getConstantState()
  {
    this.c.b = getChangingConfigurations();
    return this.c;
  }

  public int getIntrinsicHeight()
  {
    if (this.i > 0)
      return this.i;
    return this.g;
  }

  public int getIntrinsicWidth()
  {
    if (this.h > 0)
      return this.h;
    return this.f;
  }

  public int getOpacity()
  {
    Bitmap localBitmap = this.b;
    if ((localBitmap == null) || (localBitmap.hasAlpha()) || (this.c.d.getAlpha() < 255))
      return -3;
    return -1;
  }

  public Drawable mutate()
  {
    if ((!this.e) && (super.mutate() == this))
    {
      this.c = new h(this.c);
      this.e = true;
    }
    return this;
  }

  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
  }

  public void setAlpha(int paramInt)
  {
    if (paramInt != this.c.d.getAlpha())
    {
      this.c.d.setAlpha(paramInt);
      invalidateSelf();
    }
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.c.d.setColorFilter(paramColorFilter);
    invalidateSelf();
  }

  public void setDither(boolean paramBoolean)
  {
    this.c.d.setDither(paramBoolean);
    invalidateSelf();
  }

  public void setFilterBitmap(boolean paramBoolean)
  {
    this.c.d.setFilterBitmap(paramBoolean);
    invalidateSelf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.LightweightBitmapDrawable
 * JD-Core Version:    0.6.0
 */