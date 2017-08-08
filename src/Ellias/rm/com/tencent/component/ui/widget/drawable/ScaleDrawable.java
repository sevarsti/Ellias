package com.tencent.component.ui.widget.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

public class ScaleDrawable extends DrawableContainer
{
  private static final float a;
  private r b;
  private ScaleDrawable.ScaleType c;
  private Matrix d;
  private float e = 0.0F;
  private float f = 0.0F;
  private Rect g = new Rect();

  public ScaleDrawable(Drawable paramDrawable)
  {
    this(paramDrawable, null);
  }

  public ScaleDrawable(Drawable paramDrawable, ScaleDrawable.ScaleType paramScaleType)
  {
    this.b = new r(paramDrawable, this);
    a(this.b);
    a(paramScaleType);
  }

  private ScaleDrawable(r paramr, Resources paramResources)
  {
    this.b = new r(paramr, this, paramResources);
    a(this.b);
  }

  private void b()
  {
    float f1 = 0.0F;
    ScaleDrawable.ScaleType localScaleType = this.c;
    if (localScaleType == null)
    {
      if (this.d != null)
        this.d.reset();
      return;
    }
    if (this.d == null)
      this.d = new Matrix();
    int i = getIntrinsicWidth();
    int j = getIntrinsicHeight();
    int k = getBounds().width();
    int m = getBounds().height();
    switch (q.a[localScaleType.ordinal()])
    {
    default:
      return;
    case 1:
      float f30;
      float f31;
      if (i * m > k * j)
      {
        f30 = m / j;
        f31 = 0.5F * (k - f30 * i);
      }
      while (true)
      {
        this.d.setScale(f30, f30);
        this.d.postTranslate((int)(f31 + 0.5F), (int)(f1 + 0.5F));
        return;
        f30 = k / i;
        f1 = 0.5F * (m - f30 * j);
        f31 = 0.0F;
      }
    case 2:
      float f29;
      if (i * m > k * j)
        f29 = m / j;
      while (true)
      {
        this.d.setScale(f29, f29);
        this.d.postTranslate((int)(0.0F + 0.5F), (int)(0.0F + 0.5F));
        return;
        f29 = k / i;
      }
    case 3:
      float f27;
      float f28;
      if (i * m > k * j)
      {
        f27 = m / j;
        f28 = 1.0F * (k - f27 * i);
      }
      while (true)
      {
        this.d.setScale(f27, f27);
        this.d.postTranslate((int)(f28 + 0.5F), (int)(f1 + 0.5F));
        return;
        f27 = k / i;
        f1 = 1.0F * (m - f27 * j);
        f28 = 0.0F;
      }
    case 4:
      float f23;
      float f24;
      float f25;
      if (i * m > k * j)
      {
        f23 = k / i;
        float f26 = 0.5F * (m - f23 * j);
        f24 = 0.0F;
        f25 = f26;
      }
      while (true)
      {
        this.d.setScale(f23, f23);
        this.d.postTranslate((int)(f24 + 0.5F), (int)(f25 + 0.5F));
        return;
        f23 = m / j;
        f24 = 0.5F * (k - f23 * i);
        f25 = 0.0F;
      }
    case 5:
      float f22;
      if (i * m > k * j)
        f22 = k / i;
      while (true)
      {
        this.d.setScale(f22, f22);
        this.d.postTranslate((int)(0.0F + 0.5F), (int)(0.0F + 0.5F));
        return;
        f22 = m / j;
      }
    case 6:
      float f18;
      float f19;
      float f20;
      if (i * m > k * j)
      {
        f18 = k / i;
        float f21 = 1.0F * (m - f18 * j);
        f19 = 0.0F;
        f20 = f21;
      }
      while (true)
      {
        this.d.setScale(f18, f18);
        this.d.postTranslate((int)(f19 + 0.5F), (int)(f20 + 0.5F));
        return;
        f18 = m / j;
        f19 = 1.0F * (k - f18 * i);
        f20 = 0.0F;
      }
    case 7:
      float f17 = k / i;
      this.d.setScale(f17, f17);
      this.d.postTranslate((int)(0.0F + 0.5F), (int)(0.0F + 0.5F));
      return;
    case 8:
      float f15 = k / i;
      float f16 = 1.0F * (m - f15 * j);
      this.d.setScale(f15, f15);
      this.d.postTranslate((int)(0.0F + 0.5F), (int)(f16 + 0.5F));
      return;
    case 9:
      float f13 = k / i;
      float f14 = 0.5F * (m - f13 * j);
      this.d.setScale(f13, f13);
      this.d.postTranslate((int)(0.0F + 0.5F), (int)(f14 + 0.5F));
      return;
    case 10:
      float f11 = 0.5F * (m - j);
      float f12 = 0.5F * (k - i);
      this.d.postTranslate((int)(f12 + 0.5F), (int)(f11 + 0.5F));
      return;
    case 11:
    }
    float f2;
    float f4;
    int i1;
    float f6;
    float f7;
    if (i * m > k * j)
    {
      f2 = m / j;
      float f3 = 0.5F * k;
      f4 = 0.5F * m;
      int n = (int)(f2 * i);
      i1 = (int)(f2 * j);
      float f5 = this.e * n;
      f6 = this.f * i1;
      if ((n <= k) || (f5 <= f3))
        break label1065;
      float f10 = f5 - f3;
      f7 = Math.min(n - k, f10);
    }
    while (true)
    {
      float f8 = 0.0F;
      if (i1 > m)
      {
        boolean bool = f6 < f4;
        f8 = 0.0F;
        if (bool)
        {
          float f9 = f6 - f4;
          f8 = Math.min(i1 - m, f9);
        }
      }
      this.d.setScale(f2, f2);
      this.d.postTranslate(-1 * (int)(f7 + 0.5F), -1 * (int)(f8 + 0.5F));
      return;
      f2 = k / i;
      break;
      label1065: f7 = 0.0F;
    }
  }

  public void a(float paramFloat1, float paramFloat2)
  {
    if ((this.e != paramFloat1) || (this.f != paramFloat2))
    {
      this.e = paramFloat1;
      this.f = paramFloat2;
      b();
    }
  }

  public void a(ScaleDrawable.ScaleType paramScaleType)
  {
    if (this.c != paramScaleType)
    {
      this.c = paramScaleType;
      b();
    }
  }

  public void draw(Canvas paramCanvas)
  {
    Matrix localMatrix = this.d;
    if ((localMatrix == null) || (localMatrix.isIdentity()))
    {
      super.draw(paramCanvas);
      return;
    }
    int i = paramCanvas.getSaveCount();
    paramCanvas.save();
    paramCanvas.concat(localMatrix);
    super.draw(paramCanvas);
    paramCanvas.restoreToCount(i);
  }

  public Drawable.ConstantState getConstantState()
  {
    if (this.b.a())
    {
      this.b.b = getChangingConfigurations();
      return this.b;
    }
    return null;
  }

  public int getMinimumHeight()
  {
    return 0;
  }

  public int getMinimumWidth()
  {
    return 0;
  }

  protected void onBoundsChange(Rect paramRect)
  {
    int i = getIntrinsicWidth();
    int j = getIntrinsicHeight();
    if ((i > 0) && (j > 0))
    {
      paramRect = this.g;
      paramRect.set(0, 0, i, j);
    }
    super.onBoundsChange(paramRect);
    b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.ScaleDrawable
 * JD-Core Version:    0.6.0
 */