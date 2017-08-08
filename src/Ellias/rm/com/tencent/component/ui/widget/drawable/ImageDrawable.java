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

public class ImageDrawable extends Drawable
{
  private static final int a = 6;
  private Bitmap b;
  private f c;
  private int d;
  private boolean e;
  private int f = -1;
  private int g = -1;
  private int h;
  private int i;

  public ImageDrawable(Resources paramResources, Bitmap paramBitmap)
  {
    this(null, paramBitmap, -1, -1);
  }

  public ImageDrawable(Resources paramResources, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    this.c = new f(paramBitmap, paramInt1, paramInt2);
    if (paramResources != null)
      this.d = paramResources.getDisplayMetrics().densityDpi;
    b(paramBitmap);
  }

  public ImageDrawable(Bitmap paramBitmap)
  {
    this(null, paramBitmap, -1, -1);
  }

  public ImageDrawable(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    this(null, paramBitmap, paramInt1, paramInt2);
  }

  private ImageDrawable(f paramf, Resources paramResources)
  {
    this.c = new f(paramf);
    if (paramResources != null)
    {
      this.d = paramResources.getDisplayMetrics().densityDpi;
      if (paramf == null)
        break label71;
    }
    label71: for (Bitmap localBitmap = paramf.a; ; localBitmap = null)
    {
      b(localBitmap);
      return;
      if (paramf == null)
        break;
      this.d = paramf.e;
      break;
    }
  }

  protected static int a(long paramLong)
  {
    return (int)(paramLong >>> 32);
  }

  protected static long a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f1 = 1.0F;
    float f2;
    if ((paramInt3 <= 0) && (paramInt4 <= 0))
    {
      f2 = f1;
      if (f2 >= f1)
        break label68;
    }
    while (true)
    {
      return c((int)(f1 * paramInt1), (int)(f1 * paramInt2));
      if (paramInt3 * paramInt2 > paramInt4 * paramInt1)
      {
        f2 = paramInt3 / paramInt1;
        break;
      }
      f2 = paramInt4 / paramInt2;
      break;
      label68: f1 = f2;
    }
  }

  protected static int b(long paramLong)
  {
    return (int)(0xFFFFFFFF & paramLong);
  }

  protected static long c(int paramInt1, int paramInt2)
  {
    return paramInt1 << 32 | paramInt2;
  }

  private void i()
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
        break label81;
    }
    label81: for (int m = localBitmap.getHeight(); ; m = localBitmap.getScaledHeight(j))
    {
      long l = b(k, m);
      this.f = a(l);
      this.g = b(l);
      return;
      k = localBitmap.getScaledWidth(j);
      break;
    }
  }

  public int a()
  {
    if (this.h > 0)
      return this.h;
    return getIntrinsicWidth();
  }

  public void a(int paramInt)
  {
    this.h = paramInt;
  }

  public void a(int paramInt1, int paramInt2)
  {
    if ((this.c.b != paramInt1) || (this.c.c != paramInt2))
    {
      this.c.b = paramInt1;
      this.c.c = paramInt2;
      i();
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
      i();
      if (paramBoolean)
        invalidateSelf();
    }
  }

  public void a(Canvas paramCanvas)
  {
    c(paramCanvas.getDensity());
  }

  public void a(DisplayMetrics paramDisplayMetrics)
  {
    c(paramDisplayMetrics.densityDpi);
  }

  public void a(boolean paramBoolean)
  {
    this.c.f.setAntiAlias(paramBoolean);
    invalidateSelf();
  }

  public int b()
  {
    if (this.i > 0)
      return this.i;
    return getIntrinsicHeight();
  }

  protected final long b(int paramInt1, int paramInt2)
  {
    return a(paramInt1, paramInt2, this.c.b, this.c.c);
  }

  public void b(int paramInt)
  {
    this.i = paramInt;
  }

  protected final void b(Bitmap paramBitmap)
  {
    a(paramBitmap, true);
  }

  public final Paint c()
  {
    return this.c.f;
  }

  public void c(int paramInt)
  {
    if (this.d != paramInt)
    {
      if (paramInt == 0)
        paramInt = 160;
      this.d = paramInt;
      if (this.b != null)
        i();
      invalidateSelf();
    }
  }

  public int d()
  {
    return this.c.b;
  }

  public void draw(Canvas paramCanvas)
  {
    Bitmap localBitmap = this.b;
    if (localBitmap != null)
    {
      f localf = this.c;
      paramCanvas.drawBitmap(localBitmap, null, getBounds(), localf.f);
    }
  }

  public int e()
  {
    return this.c.c;
  }

  public int f()
  {
    Bitmap localBitmap = this.b;
    if (localBitmap == null)
      return -1;
    return localBitmap.getWidth();
  }

  public int g()
  {
    Bitmap localBitmap = this.b;
    if (localBitmap == null)
      return -1;
    return localBitmap.getHeight();
  }

  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.c.d;
  }

  public final Drawable.ConstantState getConstantState()
  {
    this.c.d = getChangingConfigurations();
    return this.c;
  }

  public int getIntrinsicHeight()
  {
    return this.g;
  }

  public int getIntrinsicWidth()
  {
    return this.f;
  }

  public int getOpacity()
  {
    Bitmap localBitmap = this.b;
    if ((localBitmap == null) || (localBitmap.hasAlpha()) || (this.c.f.getAlpha() < 255))
      return -3;
    return -1;
  }

  public Bitmap h()
  {
    return this.b;
  }

  public Drawable mutate()
  {
    if ((!this.e) && (super.mutate() == this))
    {
      this.c = new f(this.c);
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
    if (paramInt != this.c.f.getAlpha())
    {
      this.c.f.setAlpha(paramInt);
      invalidateSelf();
    }
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.c.f.setColorFilter(paramColorFilter);
    invalidateSelf();
  }

  public void setDither(boolean paramBoolean)
  {
    this.c.f.setDither(paramBoolean);
    invalidateSelf();
  }

  public void setFilterBitmap(boolean paramBoolean)
  {
    this.c.f.setFilterBitmap(paramBoolean);
    invalidateSelf();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.ImageDrawable
 * JD-Core Version:    0.6.0
 */